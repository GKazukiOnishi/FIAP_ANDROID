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
    fun selectAll() : List<TaskModel>

    @Query("SELECT * FROM $TASK_MODEL_TABLE_NAME WHERE status == :statusParam")
    fun selectByStatus(statusParam: TaskStatus) : List<TaskModel>

    @Insert
    fun insert(taskModel: TaskModel)

    @Update
    fun update(taskModel: TaskModel)

    @Delete
    fun delete(taskModel: TaskModel)

}