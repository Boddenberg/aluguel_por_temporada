package JuninWins.Project.controller

import JuninWins.Project.model.Booking
import JuninWins.Project.service.BookingService
import net.bytebuddy.asm.Advice.Local
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock

import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
internal class BookingControllerTest {

    val bookingService : BookingService = mock()
    var bookingController : BookingController? = null


    @Before
    fun setUp() {
        bookingController = BookingController(bookingService = bookingService)
    }


    @Test
    fun test() {
        var expectedId : Long = 1
        val bookingMock = bookingMock()
        whenever(bookingService.findBookingById(expectedId)).thenReturn(bookingMock)

        val result = bookingController!!.findBooking(expectedId)

        //assertEquals(bookingMock, result)
        assertNotNull(result)


    }

    private fun bookingMock() : Booking {

        val localDate : LocalDate = LocalDate.of(1996, 1, 18)
        val localDateAfter : LocalDate = LocalDate.of(1997, 1, 18)
        return Booking(any(), localDate, localDateAfter, 1, 1.0, any(), any())

  }

}