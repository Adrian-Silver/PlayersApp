package com.example.room_playersapp.testTutorial


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeworkTest {

//    @Test
//    fun `fib has a char`() {
//        val result = Homework.fib(abcd)
//    }

    @Test
    fun `if brace count is odd`() {

        val result = Homework.checkBraces("(8gernu")
        assertThat(result).isFalse()

    }

    @Test
    fun `if brace count is even on one side`() {

        val result = Homework.checkBraces("((8gernu")
        assertThat(result).isFalse()

    }

    @Test
    fun `if braces are equal on either side`() {
        val result = Homework.checkBraces("(())")
        assertThat(result).isTrue()

    }

    @Test
    fun `if the string is empty`() {
            val result = Homework.checkBraces("(())")
        assertThat(result).isTrue()

    }

    @Test
    fun `if no braces and string is empty`() {
        val result = Homework.checkBraces("")
        assertThat(result).isTrue()

    }
}