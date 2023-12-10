package academy.bangkit.trackmate

import academy.bangkit.trackmate.view.formatToRupiah
import org.junit.Test
import org.junit.Assert.*

class UnitTest {
    @Test
    fun testFormatToRupiah() {
        val testCases = listOf(
            10_000_000 to "Rp10.000.000",
            500_000 to "Rp500.000",
            50_000 to "Rp50.000",
            0 to "Rp0",
            123 to "Rp123",
            987_654_321 to "Rp987.654.321",
            999_999_999 to "Rp999.999.999",
            1 to "Rp1",
            1_234_567 to "Rp1.234.567",
            999 to "Rp999",
            7_777 to "Rp7.777",
            123_456_789 to "Rp123.456.789",
            1_000 to "Rp1.000",
            9_876_543 to "Rp9.876.543"
        )
        for ((amount, expectedFormattedAmount) in testCases) {
            val formattedAmount = formatToRupiah(amount)
            assertEquals(expectedFormattedAmount, formattedAmount)
        }
    }
}