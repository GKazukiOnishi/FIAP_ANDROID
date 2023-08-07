package br.com.fiap.countries.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

const val COUNTRY_MODEL_TABLE_NAME = "COUNTRY_TABLE"

@Entity(tableName = COUNTRY_MODEL_TABLE_NAME)
@Parcelize
data class CountryModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    @NonNull
    val name: String,
    @ColumnInfo
    @NonNull
    val capital: String,
    @ColumnInfo
    @NonNull
    val language: String,
    @ColumnInfo
    @NonNull
    val currency: String,
    @ColumnInfo
    @NonNull
    val location: String,
): Parcelable
