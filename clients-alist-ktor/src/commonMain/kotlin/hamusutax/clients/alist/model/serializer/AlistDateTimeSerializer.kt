package hamusutax.clients.alist.model.serializer

import hamusutax.datetime.offsetAtNow
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.char
import kotlinx.datetime.format.format
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AlistDateTimeSerializer : KSerializer<Instant> {
    private val format = DateTimeComponents.Format {
        year()
        char('-')
        monthNumber()
        char('-')
        dayOfMonth()
        char('T')
        hour()
        char(':')
        minute()
        char(':')
        second()
        char('.')
        secondFraction(7)
        offsetHours()
        char(':')
        offsetMinutesOfHour()
    }
    private val timeZone = TimeZone.currentSystemDefault()

    override val descriptor =
        PrimitiveSerialDescriptor("AlistDateTimeSerializer", STRING)

    override fun deserialize(decoder: Decoder) =
        format.parse(decoder.decodeString()).toInstantUsingOffset()

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeString(
            format.format {
                setDateTime(value.toLocalDateTime(timeZone))
                setOffset(timeZone.offsetAtNow())
            }
        )
    }
}
