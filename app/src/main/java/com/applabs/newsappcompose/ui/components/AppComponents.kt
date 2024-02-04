package com.applabs.newsappcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.applabs.newsappcompose.R
import com.applabs.newsappcompose.data.entity.Article
import com.applabs.newsappcompose.data.entity.NewsResponse
import com.applabs.newsappcompose.data.entity.Source
import com.applabs.newsappcompose.ui.theme.Purple40

@Composable
fun Loader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(color = Purple40)
    }
}

@Composable
fun NewsList(response: NewsResponse) {

    LazyColumn {
        items(response.articles) { article ->
            HeaderTextComponent(textvalue = article.title ?: "NA")
        }
    }

}

@Composable
fun NewsRowComponent(page: Int, article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {
        article.urlToImage?.let { imageUrl ->
            AsyncImage(
                model = imageUrl,
                contentDescription = "news image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_place_holder),
                error = painterResource(id = R.drawable.ic_place_holder)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            text = article.title ?: "",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 22.sp
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = article.description ?: "",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 18.sp
            ),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        AuthorAndSource(article.author, article.source.name)
    }
}

@Composable
fun NewsText(textvalue: String) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        text = textvalue,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.SansSerif,
            color = Purple40

        )
    )

}

@Composable
fun HeaderTextComponent(textvalue: String) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        text = textvalue,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    )

}

@Composable
fun AuthorAndSource(author: String?, source: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        author?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        source?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            )
        }
    }
}


@Composable
fun EmptyStateComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Using an icon or image that is friendly and relevant to "no news" state
        Icon(
            painter = painterResource(id = R.drawable.ic_empty), // Ensure you have a friendly, relevant icon
            contentDescription = "No News",
            modifier = Modifier.size(100.dp),
            tint = Color.LightGray // Soften the icon color to make it less stark
        )

        Spacer(modifier = Modifier.height(24.dp)) // Add some space between the icon and the text

        Text(
            text = "No news as of now\nPlease check back later",
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun NewsRowComponentPreview() {
    val article = Article(
        "Mr X",
        "Dummy news article",
        "Dummy description",
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300",
        "Google",
        "Dummy content",
        Source("testid", "Mr Y")
    )
    NewsRowComponent(0, article)
}