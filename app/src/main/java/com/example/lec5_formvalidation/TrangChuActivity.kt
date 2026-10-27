package com.example.lec5_formvalidation

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TrangChuActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trang_chu)

        val tvChaoMung = findViewById<TextView>(R.id.tvChaoMung)
        val tvThongTin = findViewById<TextView>(R.id.tvThongTin)
        val btnThoat = findViewById<Button>(R.id.btnThoat)

        val hoTen = intent.getStringExtra("hoTen") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val soDienThoai =
            intent.getStringExtra("soDienThoai") ?: ""

        tvChaoMung.text = "Xin chào, $hoTen!"

        tvThongTin.text = """
            THÔNG TIN NGƯỜI DÙNG
            
            Họ và tên: $hoTen
            
            Email: $email
            
            Số điện thoại: $soDienThoai
        """.trimIndent()

        btnThoat.setOnClickListener {
            finish()
        }
    }
}
