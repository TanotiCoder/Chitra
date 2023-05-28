package com.example.chitra.contoller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.chitra.model.PathWrapper

class ChitraController : ViewModel() {
    private val _undo = mutableStateListOf<PathWrapper>()
    private val _redo = mutableStateListOf<PathWrapper>()
    internal var point: SnapshotStateList<PathWrapper> = _undo

    var currentColor by mutableStateOf(Color.Red)
        private set
    var pathStroke by mutableStateOf(0.4f)
        private set

    fun insertNewPath(newPoint: Offset) {
        val pathWrapper =
            PathWrapper(points = mutableStateListOf(newPoint), currentColor, this.pathStroke)
        _undo.add(pathWrapper)

    }

    fun updatePath(latestPoint: Offset) {
        val index = _undo.lastIndex
        if (index >= 0) {
            _undo[index].points.add(latestPoint)
        }
    }

    fun undo() {
        val index = _undo.lastIndex
        if (index >= 0) {
            _redo.add(_undo[index])
            _undo.removeAt(index)
        }
    }

    fun redo() {
        val redoIndex = _redo.lastIndex
        if (redoIndex >= 0) {
            _undo.add(_redo[redoIndex])
            _redo.removeAt(redoIndex)
        }
    }

    fun currentColor(color: Color) {
        currentColor = color
    }

    fun pathStroke(value: Float) {
        pathStroke = value
    }

    fun clear() {
        _redo.clear()
        _undo.clear()
        point.clear()
    }
}

