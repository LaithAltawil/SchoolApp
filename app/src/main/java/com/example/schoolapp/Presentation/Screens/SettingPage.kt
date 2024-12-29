package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Data.setting
import com.example.schoolapp.Navigation.Screen
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R
import androidx.compose.runtime.State


//solved @LT #please|| we are not doing this 😥
//@LT:we wont be doing theme and any hard thing
@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SettingPage(
//    mainviewmodel: MainViewModel ,
//    navController: NavController?
//) {
//    val state = mainviewmodel.Settingstate.collectAsStateWithLifecycle()
//    //create A state for the settings page and add it to the main view model
//
//
//    val settings = listOf(
//        setting("Profile", painterResource(id = R.drawable.baseline_account_box_24),"Edit Profile") {
//            navController?.navigate(Screen.ProfilePage.route)
//        },
//        setting("Notifications", painterResource(id = R.drawable.baseline_notifications_24),"Notifications"){
//
//        } ,
//        setting("Help & FAQ", painterResource(id = R.drawable.help),"Help & FAQ"){} ,
//        setting("Contact us", painterResource(id = R.drawable.contact_us),"Contact us"){}
//    )
//
//
//    AppTheme {
//        Surface(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxSize(),
//            color = MaterialTheme.colorScheme.primaryContainer
//        ) {
//            Scaffold(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                topBar = {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
//                            .height(170.dp)
//                            .background(MaterialTheme.colorScheme.primary)
//                    ) {
//                        Row(
//                            modifier = Modifier.fillMaxSize(),
//
//                        ) {
//                            IconButton(
//                                modifier = Modifier.padding(top = 50.dp, start = 5.dp),
//                                onClick = {
//                                    navController?.popBackStack()
//                                }
//                            ) {
//                                Icon(
//                                    Icons.Outlined.ArrowBack,
//                                    contentDescription = null,
//                                    modifier = Modifier.size(50.dp),
//                                    tint = MaterialTheme.colorScheme.onPrimary
//                                )
//                            }
//                            Spacer(modifier = Modifier.width(20.dp))
//                            Text(
//                                text = "Settings", fontSize = 70.sp,
//                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
//                                modifier = Modifier.padding(top=40.dp),
//                                color = MaterialTheme.colorScheme.onPrimary
//                            )
//                        }
//                    }
//                },
//                // Add content padding
//            ) {
//                Column(
//                    modifier = Modifier
//                        .padding(it)
//                        .fillMaxSize()
//                ) {
//                    LazyColumn(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp)
//                    ) {
//                        itemsIndexed(settings) { index,item ->
//                            Card(
//                                colors = androidx.compose.material3.CardDefaults.cardColors(
//                                    containerColor = MaterialTheme.colorScheme.primary,
//                                    contentColor = MaterialTheme.colorScheme.onPrimary
//                                ),
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 6.dp, bottom = 6.dp)
//                                    .clip(RoundedCornerShape(20.dp))
//                                    .height(50.dp)
//                                    .width(350.dp)
//                                    .clickable {
//                                        mainviewmodel.showAlertDialog(index, true)
//
//                                    }
//                            ) {
//                                if (state.value.showAlertDialog[index]) {
//                                    AlertDialog(
//                                        onDismissRequest = {
//                                            mainviewmodel.showAlertDialog(index, false)
//
//                                        },
//                                        title = { Text("Pop-up Title") },
//                                        text = { Text("This is the pop-up content.") },
//                                        confirmButton = {
//                                            Button(onClick = {
//                                                mainviewmodel.showAlertDialog(index, false)
//
//                                            }) {
//                                                Text("OK")
//                                            }
//                                        }
//                                    )
//                                }
//                                Column(
//                                    modifier = Modifier
//                                        .padding(start = 16.dp, end = 16.dp)
//                                        .fillMaxSize(),
//                                    horizontalAlignment = Alignment.CenterHorizontally,
//                                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
//                                ) {
//                                    Row {
//                                        item.imagePath?.let { it1 ->
//                                            Icon(
//                                                painter = it1,
//                                                contentDescription = null,
//                                                modifier = Modifier.size(30.dp)
//                                            )
//                                        }
//                                        Spacer(modifier = Modifier.width(16.dp))
//                                        Row(
//                                            modifier = Modifier.fillMaxWidth(),
//                                            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
//                                            verticalAlignment = Alignment.CenterVertically
//                                        ) {
//                                            Text(text = item.name)
//                                            Icon(
//                                                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
//                                                contentDescription = null
//                                            )
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun AlertDialog(mainviewmodel: MainViewModel,index: Int){
//    AlertDialog(
//        onDismissRequest = {
//            mainviewmodel.showAlertDialog(index, false)
//
//        },
//        title = { Text("Pop-up Title") },
//        text = { Text("This is the pop-up content.") },
//        confirmButton = {
//            Button(onClick = {
//                mainviewmodel.showAlertDialog(index, false)
//
//            }) {
//                Text("OK")
//            }
//        }
//    )
//}


//completely improved version
@Composable
fun SettingPage(
    mainviewmodel: MainViewModel,
    navController: NavController?
) {
    val state = mainviewmodel.Settingstate.collectAsStateWithLifecycle()

    val settings = listOf(
        setting(
            "حسابي",
            painterResource(id = R.drawable.baseline_account_box_24),
            "Edit your personal information and preferences",
            onClick = {
                navController?.navigate(Screen.ProfilePage.route)
            }
        ),
//        setting(
//            "Notifications",
//            painterResource(id = R.drawable.baseline_notifications_24),
//            "Configure your notification preferences",
//            onClick = {}
//        ),
        setting(
            "مساعدة",
            painterResource(id = R.drawable.help),
            "Find answers to common questions",
            onClick = {}
        ),
        setting(
            "تواصل معنا",
            painterResource(id = R.drawable.contact_us),
            "Get in touch with our support team",
            onClick = {}
        )
    )

    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                topBar = { SettingsTopBar(navController) }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        itemsIndexed(settings) { index, item ->
                            SettingCard(
                                item = item,
                                index = index,
                                mainviewmodel = mainviewmodel,
                                state = state,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsTopBar(navController: NavController?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
            .height(170.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            IconButton(
                modifier = Modifier.padding(top = 50.dp, start = 5.dp),
                onClick = { navController?.popBackStack() }
            ) {
                Icon(
                    Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(50.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "تواصلوا معنا",
                fontSize = 70.sp,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                modifier = Modifier.padding(top = 40.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
private fun SettingCard(
    item: setting,
    index: Int,
    mainviewmodel: MainViewModel,
    state: State<MainViewModel.SettingsState>,
    navController: NavController? = null
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clip(RoundedCornerShape(20.dp))
            .height(50.dp)
            .clickable { mainviewmodel.showAlertDialog(index, true) }
    ) {
        // Custom dialog content for each setting
        if (state.value.showAlertDialog[index]) {
            when (index) {
                0 -> ProfileDialog(onDismiss = { navController?.navigate(Screen.ProfilePage.route) })
                1 -> NotificationsDialog(onDismiss = { mainviewmodel.showAlertDialog(index, false) })
                2 -> HelpFAQDialog(onDismiss = { mainviewmodel.showAlertDialog(index, false) })
                3 -> ContatDialog(onDismiss = { mainviewmodel.showAlertDialog(index, false) })
            }
        }

        SettingCardContent(item)
    }
}

@Composable
private fun SettingCardContent(item: setting) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = "Navigate"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.name)
                item.imagePath?.let { icon ->
                    Icon(
                        painter = icon,
                        contentDescription = item.name,
                        modifier = Modifier.size(30.dp)
                    )
                }

            }

        }
    }
}

@Composable
private fun ProfileDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {  },
        text = {
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.End) {
                Text("اضغط حسنا للذهاب الى حسابك")
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("حسنا")
            }
        }
    )
}

@Composable
private fun NotificationsDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Notification Settings") },
        text = {
            Column {
                Text("Configure your notifications:")
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Class Schedule Updates")
                Text("• Assignment Deadlines")
                Text("• Exam Reminders")
                Text("• School Announcements")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Tap OK to manage notification preferences")
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
private fun HelpFAQDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Help & FAQ") },
        text = {
            Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.End) {
                Text("الأسئلة الشائعة")
                Spacer(modifier = Modifier.height(8.dp))
                Text("• كيف يمكنني تسجيل الدخول إلى النظام؟")
                Text("للدخول إلى النظام، يجب عليك استخدام اسم المستخدم وكلمة المرور المقدمة لك من قبل المدرسة. تأكد من إدخال المعلومات بشكل صحيح وإذا واجهت أي مشكلة، يمكنك التواصل مع الدعم الفني.")
                Text("كيف يمكنني تسجيل طلاب جدد؟")
                Text("يمكن لمسؤولي النظام تسجيل طلاب جدد عن طريق الدخول إلى لوحة التحكم واختيار \"إضافة طالب جديد\". ستحتاج إلى إدخال البيانات الشخصية للطالب والمعلومات الأكاديمية المطلوبة.")
                Text("كيف يمكنني رفع الواجبات المنزلية؟")
                Text("لرفع الواجبات المنزلية، يجب على الطلاب الدخول إلى تطبيق الهاتف المحمول، اختيار المادة المناسبة، ومن ثم اختيار \"رفع الواجب\" وإرفاق الملف المطلوب.")
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
private fun ContatDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("تواصل معنا") },
        text = {
            Column {
                Text("لديك أي استفسارات أو تحتاج إلى مساعدة، يمكنك التواصل معنا عبر المعلومات التالية:")
                Spacer(modifier = Modifier.height(8.dp))
                Text("• البريد الإلكتروني: support@example.com")
                Text("الهاتف: +962 6 1234567 " )
                Text("العنوان: شارع الملك عبدالله الثاني، عمان، الأردن")
                Text("ساعات العمل:")
                Text("من 8:00 ص إلى 5:00 م")
                Text("الجمعة - السبت: مغلق")
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("حسنا")
            }
        }
    )
}



