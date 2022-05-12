package com.omolleapaza.peruapps.data.di

import androidx.room.migration.Migration
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.HashSet


val migrationsModule =  module{
    single<Set<Migration>>(named("migrations")) {
        //HashSet(arrayListOf(MIGRATION_1_2)) //this is for migrations
        HashSet(arrayListOf())
    }
}