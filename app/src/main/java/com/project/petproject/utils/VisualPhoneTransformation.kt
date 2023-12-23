package com.project.petproject.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class VisualPhoneTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Make the string XXX-XXX-XXX
        val cleanedText = text.replace("[^\\d]".toRegex(), "")

        var output = ""
        for (i in cleanedText.indices) {
            output += cleanedText[i]
            if ((i == 1) && i != cleanedText.length - 1) output += " "
            if (i == 6 && i != cleanedText.length - 1) output += "-"
        }

        val phoneNumberTransformer = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset == 1 || offset == 6) return offset - 1
                if (offset == 11) return 12
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset == 2 || offset == 8) return offset + 1
                if (offset == 12) return 11
                return offset
            }
        }

        return TransformedText(AnnotatedString(output), phoneNumberTransformer)

    }

}