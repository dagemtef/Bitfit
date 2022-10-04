import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bitfit.SleepEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDao {
    @Query("SELECT * FROM sleep_table")
    fun getAll(): Flow<List<SleepEntity>>

    @Insert
    fun insert(sleep: SleepEntity)

    @Query("DELETE FROM sleep_table")
    fun deleteAll()
}