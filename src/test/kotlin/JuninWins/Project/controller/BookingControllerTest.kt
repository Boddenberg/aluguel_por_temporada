package JuninWins.Project.controller

import JuninWins.Project.enums.StatusReservaEnum
import JuninWins.Project.model.Accommodation
import JuninWins.Project.model.Booking
import JuninWins.Project.model.Guest
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
        val expectedId : Long = 1
        whenever(bookingService.findBookingById(expectedId)).thenReturn(bookingMock())
        val result = bookingController!!.findBooking(expectedId)

        assertNotNull(result)
    }

    private fun bookingMock() : Booking {
        val guestMock = mock(Guest::class.java)
        val accommodationMock = mock(Accommodation::class.java)
        val statusReservaMock = mock(StatusReservaEnum::class.java)
        val localDate : LocalDate = LocalDate.of(1996, 1, 18)
        val localDateAfter : LocalDate = LocalDate.of(1997, 1, 18)
        return Booking(accommodationMock, localDate, localDateAfter, 1, 1.0, guestMock, statusReservaMock)

  }

}