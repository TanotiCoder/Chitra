package com.example.chitra.ui

import android.widget.SeekBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.chitra.R
import com.example.chitra.model.ModelObject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomTabBar(
    buttonIndex: (Int) -> Unit,
    currentColor: Color = Color.Red,
    sliderPosition: Float,
    sliderPositionOnSlide: (Float) -> Unit,
    showSeekBar: Boolean
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = 4.dp
        ) {
            Column {
                AnimatedVisibility(visible = showSeekBar) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(if (showSeekBar) 48.dp else 0.dp)
                    ) {
                        MySliderDemo(sliderPosition, sliderPositionOnSlide)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    Arrangement.SpaceAround,
                    Alignment.CenterVertically
                ) {
                    ModelObject.IconButtonModelList.forEachIndexed { index, it ->
                        if (index == 2) {
                            IconButton(onClick = { buttonIndex(index) }) {
                                Icon(
                                    painter = painterResource(id = it.icon),
                                    contentDescription = it.name,
                                    modifier = Modifier.border(
                                        2.dp,
                                        color = Color.Gray,
                                        shape = CircleShape
                                    ),
                                    tint = currentColor,
                                )
                            }
                        } else {
                            IconButton(onClick = { buttonIndex(index) }) {
                                Icon(
                                    painter = painterResource(id = it.icon),
                                    contentDescription = it.name,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                    IconButton(onClick = { buttonIndex(4) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_size),
                            contentDescription = "Size"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MySliderDemo(sliderPosition: Float, sliderPositionOnSlide: (Float) -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = (sliderPosition * 100).toInt().toString())
            Slider(value = sliderPosition, onValueChange = { sliderPositionOnSlide(it) })
        }
    }
}