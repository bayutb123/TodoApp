package com.dicoding.todoapp.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery

//TODO 2 : Define data access object (DAO) OK - CEK LAGI
@Dao
interface TaskDao {

    @RawQuery(observedEntities = [Task::class])
    fun getTasks(query: SupportSQLiteQuery): DataSource.Factory<Int, Task>

    @Query("SELECT * FROM tasks WHERE id=:taskId")
    fun getTaskById(taskId: Int): LiveData<Task>

    @Query("SELECT * FROM tasks ORDER BY dueDateMillis ASC")
    fun getNearestActiveTask(): Task

    @Insert
    suspend fun insertTask(task: Task): Long

    @Insert
    fun insertAll(vararg tasks: Task)

    @Query("DELETE FROM tasks WHERE id=:task")
    suspend fun deleteTask(task: Task)

    @Query("UPDATE tasks SET isCompleted=:completed")
    suspend fun updateCompleted(taskId: Int, completed: Boolean)

}
