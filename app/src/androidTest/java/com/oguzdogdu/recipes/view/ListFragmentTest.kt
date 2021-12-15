package com.oguzdogdu.recipes.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.oguzdogdu.recipes.R
import com.oguzdogdu.recipes.launchFragmentInHiltContainer
import com.oguzdogdu.recipes.presentation.listfragment.ListFragAdapter
import com.oguzdogdu.recipes.presentation.listfragment.ListFragment
import com.oguzdogdu.recipes.presentation.listfragment.ListFragmentDirections
import com.oguzdogdu.recipes.presentation.listfragment.ListViewModel
import com.oguzdogdu.recipes.repo.FakeRecipeRepository
import com.oguzdogdu.recipes.util.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ListFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun `clickImage_popBackStack`(){
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.navigation)
        val testViewModel = ListViewModel(FakeRecipeRepository())
        launchFragmentInHiltContainer<ListFragment> {
            Navigation.setViewNavController(requireView(),navController)
            navController.setCurrentDestination(R.id.listFragment)
        }

        onView(withId(R.id.list_layout)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListFragAdapter.RecipeHolder>(
                0,
                click()
            )
        )

        verify(navController)
        assertThat(testViewModel.recipeResponse).isEqualTo(Resource.success(testViewModel.recipeResponse))
    }
}


