package com.example.ucp_2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp_2.ui.view.barang.DetailBrgView
import com.example.ucp_2.ui.view.barang.HomeBrgView
import com.example.ucp_2.ui.view.barang.InsertBrgView
import com.example.ucp_2.ui.view.barang.UpdateBrgView

import com.example.ucp_2.ui.view.suplier.HomeSprView
import com.example.ucp_2.ui.view.suplier.InsertSprView
import com.example.ucp_2.ui.view.HomeTokoView as HomeTokoView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
){
    NavHost(
        navController = navController,
        startDestination = AlamatNavigasi.DestinasiHome.route
    ) {
        composable(
            route = AlamatNavigasi.DestinasiHome.route
        ) {
            HomeTokoView(
                onListBrg = { navController.navigate(AlamatNavigasi.DestinasiHomeBrg.route) },
                onListSpr = { navController.navigate(AlamatNavigasi.DestinasiHomeSpr.route) },
                onAddBrg = { navController.navigate(AlamatNavigasi.DestinasiInsertBrg.route) },
                onAddSpr = { navController.navigate(AlamatNavigasi.DestinasiInsertSpr.route) },
            )
        }
        composable(
            route = AlamatNavigasi.DestinasiInsertBrg.route
        ) {
            InsertBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
        composable(
            route = AlamatNavigasi.DestinasiInsertSpr.route
        ) {
            InsertSprView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
        composable(
            route = AlamatNavigasi.DestinasiHomeBrg.route
        ) {
            HomeBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onDetailClick = { idBrg ->
                    navController.navigate("${AlamatNavigasi.DestinasiDetailBrg.route}/$idBrg")
                    println("PengelolaHalaman: idBrg = $idBrg")
                }
            )
        }
        composable(
            route = AlamatNavigasi.DestinasiHomeSpr.route
        ) {
            HomeSprView(
                onBack = {
                    navController.popBackStack()
                         },

            )
        }
        composable(
            AlamatNavigasi.DestinasiDetailBrg.routesWithArg,
            arguments = listOf(
                navArgument(AlamatNavigasi.DestinasiDetailBrg.idBrg) {
                    type = NavType.IntType
                }
            )
        ) {
            val idBrg = it.arguments?.getInt(AlamatNavigasi.DestinasiDetailBrg.idBrg)
            println("DetailBrgView: Received idBrg = $idBrg")
            idBrg?.let {  idBrg ->
                DetailBrgView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${AlamatNavigasi.DestinasiUpdate.route}/$idBrg")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(AlamatNavigasi.DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(AlamatNavigasi.DestinasiUpdate.idBrg){
                    type = NavType.IntType
                }
            )
        ){
            UpdateBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )

        }
    }
}