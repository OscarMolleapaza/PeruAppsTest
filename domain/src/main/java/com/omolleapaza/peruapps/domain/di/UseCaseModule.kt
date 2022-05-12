package com.omolleapaza.peruapps.domain.di

import com.omolleapaza.peruapps.domain.usecase.DeletePostUseCase
import com.omolleapaza.peruapps.domain.usecase.GetPostsUseCase
import org.koin.dsl.module



val useCasesModule = module {

    //GetData
    factory { DeletePostUseCase(get()) }
    factory { GetPostsUseCase(get()) }

}