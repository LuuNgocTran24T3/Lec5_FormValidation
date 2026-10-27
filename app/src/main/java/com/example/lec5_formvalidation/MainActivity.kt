package com.example.lec5_formvalidation

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import com.example.lec5_formvalidation.model.NguoiDung

class MainActivity : Activity() {

    private lateinit var editHoTen: EditText
    private lateinit var editEmail: EditText
    private lateinit var editMatKhau: EditText
    private lateinit var editSoDienThoai: EditText
    private lateinit var rootLayout: android.view.View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.rootLayout)

        editHoTen = findViewById(R.id.editHoTen)
        editEmail = findViewById(R.id.editEmail)
        editMatKhau = findViewById(R.id.editMatKhau)
        editSoDienThoai = findViewById(R.id.editSoDienThoai)

        val btnDangKy = findViewById<Button>(R.id.btnDangKy)
        val btnHuy = findViewById<Button>(R.id.btnHuy)

        // Nút ĐĂNG KÝ
        btnDangKy.setOnClickListener {
            val hoTen = editHoTen.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val matKhau = editMatKhau.text.toString()
            val soDienThoai = editSoDienThoai.text.toString().trim()

            val loi = kiemTraDuLieu(
                hoTen, email, matKhau, soDienThoai
            )

            if (loi != null) {
                // FAIL: hiện Snackbar báo lỗi
                Snackbar.make(
                    rootLayout,
                    loi,
                    Snackbar.LENGTH_LONG
                ).setAction("ĐÓNG") {
                    // Người dùng có thể đóng thông báo
                }.show()

            } else {
                // SUCCESS: tạo đối tượng người dùng
                val nguoiDung = NguoiDung(
                    hoTen = hoTen,
                    email = email,
                    matKhau = matKhau,
                    soDienThoai = soDienThoai
                )

                // Chuyển sang trang chủ
                val intent = Intent(
                    this,
                    TrangChuActivity::class.java
                )

                intent.putExtra("hoTen", nguoiDung.hoTen)
                intent.putExtra("email", nguoiDung.email)
                intent.putExtra(
                    "soDienThoai",
                    nguoiDung.soDienThoai
                )

                startActivity(intent)
            }
        }

        // Nút HỦY: xóa trắng form
        btnHuy.setOnClickListener {
            editHoTen.text.clear()
            editEmail.text.clear()
            editMatKhau.text.clear()
            editSoDienThoai.text.clear()

            editHoTen.requestFocus()

            Snackbar.make(
                rootLayout,
                "Đã xóa dữ liệu, hãy nhập lại!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    // Hàm kiểm tra dữ liệu
    private fun kiemTraDuLieu(
        hoTen: String,
        email: String,
        matKhau: String,
        soDienThoai: String
    ): String? {

        if (hoTen.length < 3) {
            editHoTen.error = "Họ tên phải có ít nhất 3 ký tự"
            return "Vui lòng nhập họ tên hợp lệ!"
        }

        if (!android.util.Patterns.EMAIL_ADDRESS
                .matcher(email).matches()) {
            editEmail.error = "Email không đúng định dạng"
            return "Email không hợp lệ!"
        }

        if (matKhau.length < 6) {
            editMatKhau.error = "Mật khẩu phải có ít nhất 6 ký tự"
            return "Mật khẩu phải có ít nhất 6 ký tự!"
        }

        val regexSDT = Regex("^(03|05|07|08|09)\\d{8}$")

        if (!regexSDT.matches(soDienThoai)) {
            editSoDienThoai.error =
                "Số điện thoại phải đủ 10 số, đầu số 03, 05, 07, 08 hoặc 09"
            return "Số điện thoại không hợp lệ!"
        }

        return null
    }
}
