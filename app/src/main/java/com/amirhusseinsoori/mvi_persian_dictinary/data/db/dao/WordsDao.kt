package com.amirhusseinsoori.mvi_persian_dictinary.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.amirhusseinsoori.mvi_persian_dictinary.data.db.entity.Word
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingSource

@Dao
interface WordsDao {
    @Query("SELECT * FROM dictionary")
    fun getAllWords(): PagingSource<Int, Word>

    @Query("Select * from dictionary where word like '%' || :msg || '%'")
     fun searchAllWords(msg:String): PagingSource<Int, Word>


}