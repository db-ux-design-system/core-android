
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dbsystel.designsystem.foundation.theme.DesignSystemTheme

// The card components are shaded and rounded areas that give the interface depth.
// Whether static or interactive - the cards are the basis for further components and modules and can display content and actions.
@Composable
fun DSCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
            modifier = Modifier
                .border(
                        DesignSystemTheme.dimensions.border.height3xs,
                        color = DesignSystemTheme.activeColor.Basic.Border.Default.Default,
                        shape = RoundedCornerShape(DesignSystemTheme.dimensions.border.radiusSm)
                )
                .then(modifier)
    ) {
        Column(content = content)
    }
}

@Composable
@Preview(showBackground = true)
fun DSCardPreview(
    spacingDimensions: List<Dp> = listOf(
            DesignSystemTheme.dimensions.spacing.fixedSm,
            DesignSystemTheme.dimensions.spacing.fixedMd,
            DesignSystemTheme.dimensions.spacing.fixedLg
    )
) {
    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        spacingDimensions.forEach {
            DSCard(modifier = Modifier.padding(it))
            { Text("Swap Slot") }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}