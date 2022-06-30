package com.example.flowtest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "config")
data class Config(
    @ColumnInfo(name = "primary_value") val primaryValue: String?,
    @ColumnInfo(name = "secondary_value") val secondaryValue: String?,
    @PrimaryKey val uid: Int = 1,
)
