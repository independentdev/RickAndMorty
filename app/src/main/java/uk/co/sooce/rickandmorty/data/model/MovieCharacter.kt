package uk.co.sooce.rickandmorty.data.model
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "character")
data class MovieCharacter(
    @SerializedName("created")
    var created: String,
    @SerializedName("gender")
    var gender: String,
    @PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("species")
    var species: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("type")
    var type_: String,
    @SerializedName("url")
    var url: String
)