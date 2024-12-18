package com.example.ucp_2.dependenciesinjection

import android.content.Context
import com.example.ucp_2.data.database.TokoDatabase
import com.example.ucp_2.repository.LocalRepositoryBrg
import com.example.ucp_2.repository.LocalRepositorySpr
import com.example.ucp_2.repository.RepositoryBrg
import com.example.ucp_2.repository.RepositorySpr

interface InterfaceContainerApp {
    val repositoryBrg: RepositoryBrg
    val repositorySpr: RepositorySpr
}

class ContainerApp(private val context: Context): InterfaceContainerApp{
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(TokoDatabase.getTokoDatabase(context).BarangDao())
    }

    override val repositorySpr: RepositorySpr by lazy {
        LocalRepositorySpr(TokoDatabase.getTokoDatabase(context).SuplierDao())
    }
}
