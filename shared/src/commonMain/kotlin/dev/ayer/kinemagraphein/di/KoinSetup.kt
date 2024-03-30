package dev.ayer.kinemagraphein.di

import dev.ayer.kinemagraphein.data.api.di.ktorfitModule
import dev.ayer.kinemagraphein.data.repository.di.repositoriesModule
import dev.ayer.kinemagraphein.data.database.di.sqlDelightDriverModule
import dev.ayer.kinemagraphein.data.database.di.sqlDelightModule
import dev.ayer.kinemagraphein.domain.di.useCaseModules
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun startKmpKoin(
    presenterModule: Module,
    appDeclaration: KoinAppDeclaration
) {

    startKoin(appDeclaration)

    loadKoinModules(
        presenterModule,
        repositoriesModule,
        useCaseModules,
        ktorfitModule,
        sqlDelightDriverModule,
        sqlDelightModule,
    )

}

fun loadKoinModules(vararg modules: Module) = loadKoinModules(modules.toList())