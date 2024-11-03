package component;

import entity.ChiTietVe;
import entity.Ga;
import entity.Ve;
import java.time.format.DateTimeFormatter;

public class VeXuat extends javax.swing.JPanel {

    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");

    public VeXuat(Ve ve) {
        initComponents();
        lbTau.setText(ve.getChoNgoi().getTau().getMaTau());
        lbNgayDi.setText(formatDate.format(ve.getThoiGianLenTau()));
        lbGioDi.setText(formatTime.format(ve.getThoiGianLenTau()));
        lbViTriToa.setText(ve.getChoNgoi().getToa().getViTri() + "");
        lbViTriCho.setText(ve.getChoNgoi().getViTri() + "");
        lbIfCho1.setText(getcheckCho(ve));
        lbIfCho2.setText(tangVt(ve));
        lbDoiTuong.setText(ve.getKhachHang().getDoiTuong());
        lbTen.setText(ve.getKhachHang().getHoTen());
        lbCccd.setText(ve.getKhachHang().getCccd());
        lbMaVe.setText(ve.getMaVe());
        Ga gaDi = null;
        Ga gaDen = null;
        for(ChiTietVe ctv : ve.getLisChiTietVes()){
            if(ctv.isChieu()) {
                gaDi = ctv.getGa();
                lbGaDi.setText(ctv.getGa().getTenGa());
            }
            else {
                gaDen = ctv.getGa();
                lbGaDen.setText(ctv.getGa().getTenGa());
            }
        }
        if(ve.getKhuyenMai() != null)
        	lbGia.setText((int)(ve.getChoNgoi().getGia() * Math.abs(gaDi.getId() - gaDen.getId()) *(1 - ve.getKhuyenMai().getChietKhau())/1000)+".000");
        else
        	lbGia.setText((int)(ve.getChoNgoi().getGia() * Math.abs(gaDi.getId() - gaDen.getId())/1000)+".000");
    }

    private String tangVt(Ve ve) {
        if(ve.getChoNgoi().getMoTa().equalsIgnoreCase("Ngồi mềm"))
            return " ";
        if (ve.getChoNgoi().getViTri() < 3) {
            return "hòa tầng "+1;
        }
        if (ve.getChoNgoi().getViTri() < 5) {
            return "hòa tầng "+ 2;
        }
        else
            return "hòa tầng " +3;
    }

    private String getcheckCho(Ve ve) {
        if (!ve.getChoNgoi().getMoTa().equalsIgnoreCase("Ngồi mềm")) {
            return "Nằm " + ve.getChoNgoi().getMoTa() + " điều";
        }

        return ve.getChoNgoi().getMoTa();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle1 = new javax.swing.JLabel();
        lbTitle2 = new javax.swing.JLabel();
        lbTitle3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbGaDen = new javax.swing.JLabel();
        lbGaDi = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbViTriToa = new javax.swing.JLabel();
        lbTau = new javax.swing.JLabel();
        lbNgayDi = new javax.swing.JLabel();
        lbGioDi = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbViTriCho = new javax.swing.JLabel();
        lbIfCho1 = new javax.swing.JLabel();
        lbIfCho2 = new javax.swing.JLabel();
        lbDoiTuong = new javax.swing.JLabel();
        lbTen = new javax.swing.JLabel();
        lbCccd = new javax.swing.JLabel();
        lbGia = new javax.swing.JLabel();
        lbHanhKhach4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbMaVe = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lbTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle1.setText("CÔNG TY CỔ PHẦN VẬN TẢI");

        lbTitle2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lbTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle2.setText("ĐƯỜNG SẮT SÀI GÒN");

        lbTitle3.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        lbTitle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle3.setText("THẺ LÊN TÀU HỎA/BOARDING PASS");

        jPanel1.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Ga đi");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel2.setText("Ga đến");

        lbGaDen.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lbGaDen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbGaDen.setText("Long Khánh");

        lbGaDi.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lbGaDi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbGaDi.setText("Sài Gòn");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel4.setText("Tàu/Train:");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel6.setText("Ngày đi/Date:");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel7.setText("Giờ đi/Time:");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel8.setText("Toa/Coach: ");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel9.setText("Loại chổ/Class:");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel10.setText("Loại vé/Ticket:");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel11.setText("Họ tên/Name:");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel12.setText("Giấy tờ/Passport:");

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel13.setText("Giá/Price:");

        lbViTriToa.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lbViTriToa.setText("10");

        lbTau.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbTau.setText("SE01");

        lbNgayDi.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbNgayDi.setText("20/05/2024");

        lbGioDi.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbGioDi.setText("07:30");

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel17.setText("Chổ/Seat:  ");

        lbViTriCho.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lbViTriCho.setText("4");

        lbIfCho1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbIfCho1.setText("Nằm khoang 6 điều");

        lbIfCho2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbIfCho2.setText("hòa tầng 1");

        lbDoiTuong.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbDoiTuong.setText("Người lớn");

        lbTen.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbTen.setText("Đào Huy Hoàng");

        lbCccd.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbCccd.setText("092876289832");

        lbGia.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbGia.setText("20000");

        lbHanhKhach4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbHanhKhach4.setText("VNĐ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(119, 119, 119)
                        .addComponent(lbTau))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbViTriToa)))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbGioDi)
                            .addComponent(lbNgayDi)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbViTriCho))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbGia)
                                .addGap(18, 18, 18)
                                .addComponent(lbHanhKhach4))
                            .addComponent(lbCccd)
                            .addComponent(lbTen)
                            .addComponent(lbDoiTuong)
                            .addComponent(lbIfCho2)
                            .addComponent(lbIfCho1))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbGaDi, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbGaDen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbGaDen)
                    .addComponent(lbGaDi))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lbTau))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lbNgayDi))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lbGioDi))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lbViTriToa))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(lbViTriCho))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbIfCho1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIfCho2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDoiTuong)
                    .addComponent(jLabel10))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbTen))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbCccd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lbGia)
                    .addComponent(lbHanhKhach4))
                .addGap(17, 17, 17))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Mã vé/TicketID: ");

        lbMaVe.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lbMaVe.setText("9827347239847239");

        jPanel2.setOpaque(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("-----------------------------------------------------------");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("-----------------------------------------------------------");

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel15.setText("Thông tin liên hệ: Đại lý bán vé tàu Aplus - Số 64 Đ. Cộng Hòa, Phường 4, Tân bình, Thành");

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel16.setText("phố Hồ Chí Minh. Hotline: 18009898.");

        jLabel18.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel18.setText("Lưu ý:");

        jLabel19.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel19.setText("Để đảm bảo quyền lợi của mình, quý khách vui lòng mang theo vé và giấy tờ tùy thân ");

        jLabel20.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel20.setText("ghi trong vé trong suốt thời gian hành trình và xuất trình cho nhân viên soát vé khi có yêu cầu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTitle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbMaVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbTitle2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTitle3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbMaVe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(3, 3, 3)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel5))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbCccd;
    private javax.swing.JLabel lbDoiTuong;
    private javax.swing.JLabel lbGaDen;
    private javax.swing.JLabel lbGaDi;
    private javax.swing.JLabel lbGia;
    private javax.swing.JLabel lbGioDi;
    private javax.swing.JLabel lbHanhKhach4;
    private javax.swing.JLabel lbIfCho1;
    private javax.swing.JLabel lbIfCho2;
    private javax.swing.JLabel lbMaVe;
    private javax.swing.JLabel lbNgayDi;
    private javax.swing.JLabel lbTau;
    private javax.swing.JLabel lbTen;
    private javax.swing.JLabel lbTitle1;
    private javax.swing.JLabel lbTitle2;
    private javax.swing.JLabel lbTitle3;
    private javax.swing.JLabel lbViTriCho;
    private javax.swing.JLabel lbViTriToa;
    // End of variables declaration//GEN-END:variables
}
