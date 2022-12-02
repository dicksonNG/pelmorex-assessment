package com.example.pelmorexassessment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pelmorexassessment.ui.contactus.ContactUsViewModel
import com.example.pelmorexassessment.utils.MainCoroutineScopeRule
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ContactUsFieldValidTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()
    val contactUsViewModel = ContactUsViewModel()

    @Test
    fun contactus_all_field_correct() {
        contactUsViewModel.setPhoneNumber("6045059899")
        contactUsViewModel.setName("Tsz Ho")
        contactUsViewModel.setEmail("tszhng0605@gmail.com")
        assertEquals(true, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_phone_with_characters__incorrect() {
        contactUsViewModel.setPhoneNumber("60450598er")
        contactUsViewModel.setName("Tsz Ho")
        contactUsViewModel.setEmail("tszhng0605@gmail.com")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_phone_with_less10_number_incorrect() {
        contactUsViewModel.setPhoneNumber("60450598")
        contactUsViewModel.setName("Tsz Ho")
        contactUsViewModel.setEmail("tszhng0605@gmail.com")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_phone_with_empty_incorrect() {
        contactUsViewModel.setPhoneNumber("")
        contactUsViewModel.setName("Tsz Ho")
        contactUsViewModel.setEmail("tszhng0605@gmail.com")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_email_format2_correct() {
        contactUsViewModel.setPhoneNumber("6045059899")
        contactUsViewModel.setName("Tsz Ho")
        contactUsViewModel.setEmail("tszhng0605@gmail.ca")
        assertEquals(true, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_email_with_empty_incorrect() {
        contactUsViewModel.setPhoneNumber("6045059899")
        contactUsViewModel.setName("Tsz Ho")
        contactUsViewModel.setEmail("")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_email_with_not_email_format1_incorrect() {
        contactUsViewModel.setPhoneNumber("6045059899")
        contactUsViewModel.setName("Tsz Ho")
        contactUsViewModel.setEmail("iairj2394jasd")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_email_with_not_email_format2_incorrect() {
        contactUsViewModel.setPhoneNumber("6045059899")
        contactUsViewModel.setName("Tsz Ho")
        contactUsViewModel.setEmail("iairj@erqr")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_email_with_not_email_format3_incorrect() {
        contactUsViewModel.setPhoneNumber("6045059899")
        contactUsViewModel.setName("Tsz Ho")
        contactUsViewModel.setEmail("iairj@erqr,isad")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_email_with_not_email_format4_incorrect() {
        val contactUsViewModel = ContactUsViewModel()
        contactUsViewModel.setPhoneNumber("6045059899")
        contactUsViewModel.setName("Tsz Ho")
        contactUsViewModel.setEmail("iairj@gmail.e")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_name_with_number_incorrect() {
        contactUsViewModel.setPhoneNumber("6045059899")
        contactUsViewModel.setName("Tszh31413")
        contactUsViewModel.setEmail("tszhng0605@gmail.com")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_name_with_less4_incorrect() {
        contactUsViewModel.setPhoneNumber("6045059899")
        contactUsViewModel.setName("Tsz")
        contactUsViewModel.setEmail("tszhng0605@gmail.com")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }

    @Test
    fun contactus_name_with_empty_incorrect() {
        contactUsViewModel.setPhoneNumber("6045059899")
        contactUsViewModel.setName("")
        contactUsViewModel.setEmail("tszhng0605@gmail.com")
        assertEquals(false, contactUsViewModel.isFieldValid())
    }
}