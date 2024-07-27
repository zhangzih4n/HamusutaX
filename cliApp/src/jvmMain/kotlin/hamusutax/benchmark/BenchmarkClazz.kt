package hamusutax.benchmark

import hamusutax.formats.bittorrent.BitTorrentV2Digest
import hamusutax.io.path.source
import hamusutax.io.path.toPath
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.BenchmarkTimeUnit
import kotlinx.benchmark.Measurement
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import kotlinx.benchmark.TearDown
import kotlinx.benchmark.Warmup

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 500, timeUnit = BenchmarkTimeUnit.MILLISECONDS)
@Measurement(iterations = 20, time = 1, timeUnit = BenchmarkTimeUnit.SECONDS)
@State(Scope.Benchmark)
class BenchmarkClazz {
//    private val size = 100000
//    private val longList = mutableListOf<Long>()
    private val md = BitTorrentV2Digest()
    private val source = "D:\\Downloads\\PowerShell-7.4.4-win-x64.msi".toPath().source()

    @Setup
    fun prepare() {
//        repeat(size) {
//            longList.add(Random.nextLong(Long.MIN_VALUE, Long.MAX_VALUE))
//        }

    }

    @TearDown
    fun cleanup() {
//        longList.clear()
    }

    @Benchmark
    fun benchmarkMethod() {
        md.update(source)
        val (piecesRoot, pieces) = md.digest()
        println(piecesRoot.toHexString())
    }

    /*@Benchmark
    fun benchmarkMethod2() {
        val buffer = Buffer()
        longList.forEach { long ->
            val bytes = ByteArray(8)
            for (n in bytes.indices) {
                bytes[n] = (long shr (7 - n) * 8).toByte()
            }
            buffer.write(bytes)
        }
    }*/
}
