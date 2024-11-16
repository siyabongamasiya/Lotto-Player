package lotto.project.lottoplayer.Presentation.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import lotto.project.lottoplayer.Presentation.Components.AnimatedNumbers
import lotto.project.lottoplayer.Presentation.Uitls.LottoTypes


@Composable
fun homescreen(homeViewModel: HomeViewModel = viewModel()) {
    val Numbers by homeViewModel.numbers.collectAsState()
    val showNumbers = Numbers.isNotEmpty()
    var numberTypes by remember { mutableStateOf(LottoTypes.LOTTO) }

    // Gradient Background Colors
    val gradientColors = listOf(
        Color(0xFF2196F3), // Light Blue
        Color(0xFF3F51B5)  // Deep Purple
    )

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = gradientColors,
                                    startY = 0f,
                                    endY = 1000f
                                )
                            )
                            .padding(padding)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Button(
                            onClick = {
                                numberTypes = LottoTypes.LOTTO
                                homeViewModel.generateLottoNumbers()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF9A825),
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Generate Lotto Numbers")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                numberTypes = LottoTypes.DAILY
                                homeViewModel.generateDailyNumbers()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4CAF50),
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Generate Daily Lotto Numbers")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                numberTypes = LottoTypes.POWERBALL
                                homeViewModel.generatePowerNumbers()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD32F2F),
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Generate PowerBall Numbers")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        AnimatedNumbers(
                            showNumbers = showNumbers,
                            numberType = numberTypes,
                            numbers = Numbers
                        )
                    }
                }
            )
        }
    }
}