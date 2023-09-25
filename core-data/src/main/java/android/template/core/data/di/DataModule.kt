/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.template.core.data.di

import android.app.Application
import android.content.Context
import android.template.core.data.TicketRepository
import android.template.core.data.TicketRepositoryImpl
import android.template.core.database.TicketDatabase
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideTicketDatabase(app: Application): TicketDatabase {
        return Room.databaseBuilder(
            app,
            TicketDatabase::class.java,
            TicketDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTicketRepository(db: TicketDatabase, @ApplicationContext context: Context): TicketRepository {
        return TicketRepositoryImpl(db.ticketDao, context)
    }
}