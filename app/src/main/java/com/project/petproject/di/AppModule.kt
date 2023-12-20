package com.project.petproject.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import com.project.petproject.model.DataModel
import com.project.petproject.model.repository.UserRepositoryImpl
import com.project.petproject.viewmodel.add_edit_user.AddEditUserViewModelFactory
import com.project.petproject.viewmodel.repository.deleteUser
import com.project.petproject.viewmodel.repository.getUsers
import com.project.petproject.viewmodel.repository.insertUser
import com.project.petproject.viewmodel.repository.updateUser
import com.project.petproject.viewmodel.repository.userRepository.UserRepository
import com.project.petproject.viewmodel.use_case.UsersUseCases
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDataBase(app: Application): DataModel {
        return Room.databaseBuilder(
            app,
            DataModel::class.java,
            DataModel.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: DataModel): UserRepository {
        return UserRepositoryImpl(db.userDao())
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: UserRepository): UsersUseCases {
        return UsersUseCases(
            deleteUser = deleteUser(repository),
            insertUser = insertUser(repository),
            updateUser = updateUser(repository),
            getUser = getUsers(repository)
        )
    }
    @Provides
    fun provideAddEditUserViewModelFactory(): ViewModelProvider.Factory {
        return AddEditUserViewModelFactory()
    }
}