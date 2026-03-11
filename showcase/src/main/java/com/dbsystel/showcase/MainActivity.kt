package com.dbsystel.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.dbsystel.designsystem.components.button.DBButton
import com.dbsystel.designsystem.components.button.DBButtonVariant
import com.dbsystel.designsystem.components.card.DBCard
import com.dbsystel.designsystem.foundation.theme.DBTheme
import com.dbsystel.designsystem.foundation.theme.core.DBAdaptiveLayout

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DBTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = DBTheme.activeColor.Basic.Background.Level1.Default,
                ) { innerPadding ->

                    SemanticView(Modifier.padding(innerPadding)) {
                        DemoContent()
                    }
                }
            }
        }
    }
}

@Composable
fun SemanticView(
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    val activeColor = DBTheme.activeColor
    val brandColor = DBTheme.colors.brand
    var selectedColorVariant by remember {
        mutableStateOf(activeColor)
    }
    Column(
        modifier = modifier.padding(DBTheme.dimensions.spacing.fixedMd),
        verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedMd),
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedXs),
            verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedXs),
        ) {
            listOf(
                DBTheme.colors.neutral,
                DBTheme.colors.critical,
                DBTheme.colors.informational,
                DBTheme.colors.warning,
                DBTheme.colors.successful,
            )
                .zip(listOf("Neutral", "Critical", "Informational", "Warning", "Successful"))
                .forEach { (color, name) ->
                    DBAdaptiveLayout(
                        color = color,
                    ) {
                        DBButton(
                            text = name,
                            variant = DBButtonVariant.FILLED,
                        ) {
                            selectedColorVariant = color
                        }
                    }
                }
            DBButton(
                text = "Brand",
                variant = DBButtonVariant.BRAND,
            ) {
                selectedColorVariant = brandColor
            }
        }
        DBAdaptiveLayout(
            color = selectedColorVariant,
        ) {
            content()
        }
    }
}

@Composable
fun DemoContent() {
    DBCard {
        Column(
            verticalArrangement = Arrangement.spacedBy(DBTheme.dimensions.spacing.fixedXs),
        ) {
            Text(
                text = "Hello DB UX DS v3!",
                style = DBTheme.typography.h1,
                color = DBTheme.activeColor.onBgBasicEmphasis100Default,
            )

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                style = DBTheme.typography.bodyMd,
                color = DBTheme.activeColor.onBgBasicEmphasis100Default,
            )

            DBButton(
                text = "Login",
                variant = DBButtonVariant.FILLED,
                onClick = {},
            )
        }
    }
}
