package com.example.ucp_2.ui.navigation

interface AlamatNavigasi{
    val route: String

    object  DestinasiHome : AlamatNavigasi{
        override val route = "Home"
    }

    object DestinasiUpdate : AlamatNavigasi{
        override val route = "Update"
        const val idBrg = "idBrg"
        val routesWithArg = "$route/{$idBrg}"
    }

    object DestinasiInsertBrg : AlamatNavigasi{
        override val route: String = "insertbrg"
    }

    object DestinasiInsertSpr : AlamatNavigasi{
        override val route: String = "insertspr"
    }

    object DestinasiHomeBrg : AlamatNavigasi {
        override val route = "Barang"
    }

    object DestinasiHomeSpr : AlamatNavigasi {
        override val route = "HomeSupplier"

    }

    object DestinasiDetailBrg : AlamatNavigasi {
        override val route = "detailbarang"
        const val idBrg = "idBrg"
        val routesWithArg = "$route/{$idBrg}"
    }

}