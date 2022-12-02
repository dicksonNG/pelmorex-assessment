package com.example.pelmorexassessment

import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.pelmorexassessment.test.launchFragmentInHiltContainer
import com.example.pelmorexassessment.ui.contactus.ContactUsFragment
import com.example.pelmorexassessment.ui.gallery.GalleryFragment
import com.example.pelmorexassessment.ui.gallery.GalleryFullScreenFragment
import com.example.pelmorexassessment.ui.gallery.GalleryListAdapter
import com.example.pelmorexassessment.ui.weather.WeatherFragment
import com.example.pelmorexassessment.utils.MainCoroutineScopeRule
import com.google.android.material.switchmaterial.SwitchMaterial
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
class FragmentTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    @Throws(Exception::class)
    fun contactUsFragmentShouldNotBeNull() {
        launchFragmentInHiltContainer<ContactUsFragment> {
            assert(this.view?.findViewById<TextView>(R.id.tvSend)?.text.toString() == "Send")
            assert(this.view?.findViewById<TextView>(R.id.tvTitle)?.text.toString() == "Contact Us")
            assert(
                this.view?.findViewById<com.example.pelmorexassessment.view.TitleEditTextView>(R.id.tvPhoneNumber)
                    ?.findViewById<TextView>(R.id.vPhoneRegionCode)?.text.toString() == "+1"
            )
            assert(
                this.view?.findViewById<com.example.pelmorexassessment.view.TitleEditTextView>(R.id.tvName)
                    ?.findViewById<TextView>(R.id.vInputTextTitle)?.text.toString() == "Name"
            )
            assert(
                this.view?.findViewById<com.example.pelmorexassessment.view.TitleEditTextView>(R.id.tvEmail)
                    ?.findViewById<TextView>(R.id.vInputTextTitle)?.text.toString() == "Email"
            )
            assert(
                this.view?.findViewById<com.example.pelmorexassessment.view.TitleEditTextView>(R.id.tvPhoneNumber)
                    ?.findViewById<TextView>(R.id.vInputTextTitle)?.text.toString() == "Phone Number"
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun galleryFragmentShouldNotBeNull() {
        launchFragmentInHiltContainer<GalleryFragment> {
            assert(this.view?.findViewById<TextView>(R.id.tvTitle)?.text.toString() == "Gallery")
            assert(this.view?.findViewById<RecyclerView>(R.id.rvGallery)?.adapter is GalleryListAdapter)
        }
    }

    @Test
    @Throws(Exception::class)
    fun galleryFullScreenFragmentShouldNotBeNull() {
        launchFragmentInHiltContainer<GalleryFullScreenFragment> {
            assert(this.view?.findViewById<ImageView>(R.id.ivClose)?.drawable != null)
            assert(this.view?.findViewById<ViewPager2>(R.id.vpGallery)?.adapter is GalleryListAdapter)
        }
    }

    @Test
    @Throws(Exception::class)
    fun galleryWeatherFragmentShouldNotBeNull() {
        launchFragmentInHiltContainer<WeatherFragment> {
            assert(this.view?.findViewById<TextView>(R.id.tvTitle)?.text.toString() == "Weather")
            assert(this.view?.findViewById<AppCompatSpinner>(R.id.spCityList)?.adapter is ArrayAdapter<*>)
            assert(this.view?.findViewById<TextView>(R.id.cityName)?.text != null)
            assert(this.view?.findViewById<TextView>(R.id.date)?.text != null)
            assert(this.view?.findViewById<TextView>(R.id.tvWeatherDescription)?.text != null)
            assert(this.view?.findViewById<TextView>(R.id.weatherTemp)?.text != null)
            assert(this.view?.findViewById<TextView>(R.id.tvWeatherFeelLike)?.text != null)
            assert(this.view?.findViewById<SwitchMaterial>(R.id.swTemp) != null)

        }
    }
}