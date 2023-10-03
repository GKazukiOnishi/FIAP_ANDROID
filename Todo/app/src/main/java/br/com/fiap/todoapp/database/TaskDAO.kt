package br.com.fiap.todoapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.todoapp.TaskStatus

@Dao
interface TaskDAO {

    @Query("SELECT * FROM $TASK_MODEL_TABLE_NAME ORDER BY id DESC")
    suspend fun selectAll() : List<TaskModel>

    @Query("SELECT * FROM $TASK_MODEL_TABLE_NAME WHERE status == :statusParam")
    suspend fun selectByStatus(statusParam: TaskStatus) : List<TaskModel>

    @Insert
    suspend fun insert(taskModel: TaskModel)

    @Update
    suspend fun update(taskModel: TaskModel)

    @Delete
    suspend fun delete(taskModel: TaskModel)

}