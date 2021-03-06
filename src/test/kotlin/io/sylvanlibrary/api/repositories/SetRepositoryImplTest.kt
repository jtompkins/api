package io.sylvanlibrary.api.repositories

import io.sylvanlibrary.api.daos.SetDao
import io.sylvanlibrary.api.fixtures.SetFixture
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import org.skife.jdbi.v2.DBI
import java.util.*

class SetRepositoryImplTest {
  @Mock lateinit var dbiMock: DBI
  @Mock lateinit var setDaoMock: SetDao

  lateinit var classUnderTest: SetRepositoryImpl

  companion object {
    const val TEST_ABBR = "TST"
    const val TEST_NAME = "TEST"
  }

  @Before
  fun setUp() {
    initMocks(this)

    `when`(dbiMock.open(SetDao::class.java)).thenReturn(setDaoMock)

    classUnderTest = SetRepositoryImpl(dbiMock)
  }

  @Test
  fun testAll_itReturnsAListOfSets() {
    val expectedResult = listOf(SetFixture.SET)

    `when`(setDaoMock.all()).thenReturn(expectedResult)

    val result = classUnderTest.all()

    assertThat(result, equalTo(expectedResult))
  }

  @Test
  fun testAll_itInvokesTheDaoMethod() {
    classUnderTest.all()
    verify(setDaoMock, times(1)).all()
  }

  @Test
  fun testAll_itClosesTheDao() {
    classUnderTest.all()
    verify(setDaoMock, times(1)).close()
  }

  @Test
  fun testByAbbr_itReturnsAnOptionalOfSets() {
    val expectedResult = Optional.of(SetFixture.SET)

    `when`(setDaoMock.byAbbr(TEST_ABBR)).thenReturn(SetFixture.SET)

    val result = classUnderTest.byAbbr(TEST_ABBR)

    assertThat(result, equalTo(expectedResult))
  }

  @Test
  fun testByAbbr_itInvokesTheDaoMethod() {
    classUnderTest.byAbbr(TEST_ABBR)
    verify(setDaoMock, times(1)).byAbbr(TEST_ABBR)
  }

  @Test
  fun testByAbbr_itClosesTheDao() {
    classUnderTest.byAbbr(TEST_ABBR)
    verify(setDaoMock, times(1)).close()
  }

  @Test
  fun testByName_itReturnsAListOfSets() {
    val expectedResult = listOf(SetFixture.SET)

    `when`(setDaoMock.byName(TEST_NAME)).thenReturn(expectedResult)

    val result = classUnderTest.byName(TEST_NAME)

    assertThat(result, equalTo(expectedResult))
  }

  @Test
  fun testByName_itInvokesTheDaoMethod() {
    classUnderTest.byName(TEST_NAME)
    verify(setDaoMock, times(1)).byName(TEST_NAME)
  }

  @Test
  fun testByName_itClosesTheDao() {
    classUnderTest.byName(TEST_NAME)
    verify(setDaoMock, times(1)).close()
  }
}
