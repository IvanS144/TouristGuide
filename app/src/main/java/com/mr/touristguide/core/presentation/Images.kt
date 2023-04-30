package com.mr.touristguide.core.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.mr.touristguide.R
import com.mr.touristguide.core.data.remote.UnsplashImage
import com.mr.touristguide.ui.theme.HeartRed

@Composable
fun UnsplashItem(unsplashImage: UnsplashImage) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(unsplashImage.urls.regular)
            .crossfade(1000)
            .error((R.drawable.ic_placeholder))
            .placeholder(R.drawable.ic_placeholder)
            .build()
    )
    val context = LocalContext.current
    Surface(modifier = Modifier.shadow(elevation = 5.dp), shape = RoundedCornerShape(8.dp)) {
        Box(
            modifier = Modifier
//            .clickable {
//                val browserIntent = Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("https://unsplash.com/@${unsplashImage.user.username}?utm_source=DemoApp&utm_medium=referral")
//                )
//                ContextCompat.startActivity(context, browserIntent, null)
//            }
                .height(300.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = "Unsplash Image",
                contentScale = ContentScale.Crop
            )
            Surface(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .alpha(0.5f),
                color = Color.Black
            ) {}
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
                    .clickable {
                        val browserIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://unsplash.com/@${unsplashImage.user.username}?utm_source=DemoApp&utm_medium=referral")
                        )
                        ContextCompat.startActivity(context, browserIntent, null)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("${stringResource(id = R.string.photo_by)} ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                            append(unsplashImage.user.username)
                        }
                        append(" ${stringResource(id = R.string.on_unsplash)}")
                    },
                    color = Color.White,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
//                LikeCounter(
//                    modifier = Modifier.weight(3f),
//                    painter = painterResource(id = R.drawable.ic_heart),
//                    likes = "${unsplashImage.likes}"
//                )
            }
        }
    }
}

@Composable
fun LikeCounter(
    modifier: Modifier,
    painter: Painter,
    likes: String
) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            painter = painter,
            contentDescription = stringResource(id = R.string.heart_icon),
            tint = HeartRed
        )
        Divider(modifier = Modifier.width(6.dp))
        Text(
            text = likes,
            color = Color.White,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ImagesList(items: LazyPagingItems<UnsplashImage>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = items, key = { unsplashImage -> unsplashImage.id }) { unsplashImage ->
            unsplashImage?.let { UnsplashItem(unsplashImage = it) }
        }
    }
}

@Composable
fun FullscreenImage(unsplashImage: UnsplashImage) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(unsplashImage.urls.regular)
            .crossfade(1000)
            .error((R.drawable.ic_placeholder))
            .placeholder(R.drawable.ic_placeholder)
            .build()
    )
    val context = LocalContext.current
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painter,
        contentDescription = stringResource(id = R.string.unsplash_image),
        contentScale = ContentScale.Fit
    )
    Row(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
            .clickable {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://unsplash.com/@${unsplashImage.user.username}?utm_source=DemoApp&utm_medium=referral")
                )
                ContextCompat.startActivity(context, browserIntent, null)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.photo_by))
                withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                    append(unsplashImage.user.username)
                }
                append(stringResource(id = R.string.on_unsplash))
            },
            color = Color.White,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        LikeCounter(
            modifier = Modifier.weight(3f),
            painter = painterResource(id = R.drawable.ic_heart),
            likes = "${unsplashImage.likes}"
        )
    }
}