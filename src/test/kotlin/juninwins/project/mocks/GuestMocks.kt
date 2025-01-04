package juninwins.project.mocks

import juninwins.project.model.guest.Guest

object GuestMocks {

    fun guestList(): List<Guest> {
        return listOf(guestMock())
    }

    fun guestMock(): Guest {
        return Guest(
            cpf = "46290103864",
            name = "Demi",
            lastName = "Lovato",
            email = "demi@lovato.com"
        )
    }


}