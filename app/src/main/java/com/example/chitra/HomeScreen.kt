package com.example.chitra

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.chitra.contoller.ChitraController
import com.example.chitra.ui.BottomTabBar
import com.example.chitra.ui.ChitraBox
import com.example.chitra.ui.ColorPickerAlertBox


@Composable
fun HomeScreen(chitraController: ChitraController) {

    var currentColor by remember { mutableStateOf(chitraController.currentColor) }
    var openDialog by remember { mutableStateOf(false) }
    var showSeekBar by remember { mutableStateOf(false) }
    var sliderPosition by remember { mutableStateOf(chitraController.pathStroke) }
    var toolsVisibility by remember { mutableStateOf(true) }

    Scaffold(
        Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(visible = toolsVisibility) {
                BottomTabBar(
                    buttonIndex = {
                        when (it) {
                            0 -> {
                                showSeekBar = false
                                chitraController.undo()
                            }
                            1 -> {
                                showSeekBar = false
                                chitraController.redo()
                            }
                            2 -> {
                                openDialog = !openDialog
                                showSeekBar = false
                            }
                            3 -> {
                                chitraController.clear()
                            }
                            4 -> {
                                showSeekBar = !showSeekBar
                            }
                            else -> {}
                        }
                    },
                    currentColor = currentColor,
                    sliderPosition = sliderPosition,
                    sliderPositionOnSlide = {
                        sliderPosition = it
                        chitraController.pathStroke(it)
                    },
                    showSeekBar = showSeekBar
                )
            }
        },
    ) { paddingValues ->
        ChitraBox(
            modifier = Modifier.padding(paddingValues),
            showSeekBar = { showSeekBar = false },
            insertNewPath = {
                chitraController.insertNewPath(it)
            },
            updatePath = {
                chitraController.updatePath(it)
            },
            pointPathSnapshotStateList = chitraController.point,
            pointPathOnClick = { chitraController.point = it },
            onDragEnd = { toolsVisibility = !toolsVisibility }
        )
        if (openDialog) {
            ColorPickerAlertBox(
                sentCurrentColor = {
                    currentColor = it
                    chitraController.currentColor(color = it)
                },
                onClickDialog = { openDialog = !openDialog },
                currentColor = currentColor
            )
        }
    }
}