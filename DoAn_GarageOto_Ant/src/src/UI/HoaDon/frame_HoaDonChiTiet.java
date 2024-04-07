/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package src.UI.HoaDon;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import src.Model.HangHoa;
import src.Model.HoaDon;
import src.Model.HoaDonChiTiet;
import src.Service.ChiNhanhServive;
import src.Service.HangHoaService;
import src.Service.HoaDonChiTietService;
import src.Service.HoaDonService;
import src.UI.TrangChu;
import src.Util.Util;

/**
 *
 * @author WINDOWS 10
 */
public class Frame_HoaDonChiTiet extends javax.swing.JFrame {
    private HoaDon hoaDon;
    private HoaDonService hoaDonService = new HoaDonService();
    private HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    private ChiNhanhServive chiNhanhServive = new ChiNhanhServive();
    private HangHoaService hangHoaService = new HangHoaService();
    private Util util = new Util();
    private String tenNhanVien;
    private String tenDoiTac;
    /**
     * Creates new form frame_HoaDonChiTiet
     */
    public Frame_HoaDonChiTiet() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public Frame_HoaDonChiTiet(HoaDon hoaDon, String tenNhanVien, String tenDoiTac) {
        this.hoaDon = hoaDon;
        this.tenNhanVien= tenNhanVien;
        this.tenDoiTac = tenDoiTac;
        
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        hienThiThongTinHoaDon();
        hienThiDanhSachHoaDonChiTiet();
    }
    
    private void hienThiThongTinHoaDon(){
        try {
            lbHoaDonChiTiet_chiNhanh.setText(chiNhanhServive.hienThiChiNhanhTheoMaChiNhanh(hoaDon.getMaChiNhanh()).getTenChiNhanh());
        } catch (SQLException ex) {
            Logger.getLogger(Frame_HoaDonChiTiet.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbHoaDonChiTiet_maHoaDon.setText(hoaDon.getMaHoaDon());
        lbHoaDonChiTiet_nguoiTao.setText(hoaDon.getMaNhanVien() + " " + tenNhanVien);
        if (hoaDon.getMaNhaCungCap() == null){
            lbHoaDonChiTiet_doiTac.setText("Khách hàng: ");  
        }  else {
            lbHoaDonChiTiet_doiTac.setText("Nhà cung cấp: ");  
        }
        lbHoaDonChiTiet_tenDoiTac.setText(tenDoiTac);
        lbHoaDonChiTiet_thoiGian.setText(util.localDateParseMethod(hoaDon.getThoiGian()));
        lbHoaDonChiTiet_trangThai.setText(hoaDon.getTrangThai());
        tareHoaDonChiTiet_ghiChu.setText(hoaDon.getGhiChu());
        lbHoaDonChiTiet_giamGia.setText(String.valueOf(hoaDon.getGiamGia()));
        lbHoaDonChiTiet_tongTienHang.setText(String.valueOf(hoaDon.getTongTien()));
        lbHoaDonChiTiet_tienCanTra.setText(String.valueOf(hoaDon.getTongTien() - hoaDon.getGiamGia()));
        lbHoaDonChiTiet_soLuong.setText(String.valueOf("hehe"));
        lbHoaDonChiTiet_tienDaTra.setText(String.valueOf(hoaDon.getTienDaTra()));
        lbHoaDonChiTiet_tienThua.setText(String.valueOf(hoaDon.getTienDaTra() - (hoaDon.getTongTien() - hoaDon.getGiamGia())));
    }
    
    private void hienThiDanhSachHoaDonChiTiet(){
        int soLuong = 0;
        try {
            List<HoaDonChiTiet> danhSachHoaDonChiTiet = hoaDonChiTietService.hienThiHoaDonChiTietTheoMaHoaDon(this.hoaDon.getMaHoaDon());
            DefaultTableModel recordTable = (DefaultTableModel)tbHoaDonChiTiet_danhSachHoaDonChiTiet.getModel();
            recordTable.setRowCount(0);
            for (HoaDonChiTiet hoaDonChiTiet : danhSachHoaDonChiTiet) {
                Vector columnData = new Vector();
                columnData.add(hoaDonChiTiet.getMaHangHoa());
                columnData.add(hangHoaService.hienThiHangHoaTheoMaHangHoa(hoaDonChiTiet.getMaHangHoa()).getTenHangHoa());
                columnData.add(String.valueOf(hoaDonChiTiet.getSoLuong()));
                soLuong += hoaDonChiTiet.getSoLuong();
                columnData.add(String.valueOf(hoaDonChiTiet.getGiaBan()));
                columnData.add(String.valueOf(hoaDonChiTiet.getGiamGia()));
                columnData.add(String.valueOf(hoaDonChiTiet.getThanhTien()));
                recordTable.addRow(columnData);
            }
            lbHoaDonChiTiet_soLuong.setText(String.valueOf(soLuong));
            TableColumnModel columnModel = tbHoaDonChiTiet_danhSachHoaDonChiTiet.getColumnModel();
        // Get the column at the desired index
            TableColumn columnSL = columnModel.getColumn(2);
            // Set the preferred width of the column
            columnSL.setMaxWidth(75);
            columnSL.setMinWidth(75);
            
            TableColumn columnDonGia = columnModel.getColumn(3);
            // Set the preferred width of the column
            columnDonGia.setMaxWidth(100);
            columnDonGia.setMinWidth(100);
            
            TableColumn columnGiamGia = columnModel.getColumn(4);
            // Set the preferred width of the column
            columnGiamGia.setMaxWidth(100);
            columnGiamGia.setMinWidth(100);
            
            TableColumn columnMaHang = columnModel.getColumn(0);
            // Set the preferred width of the column
            columnMaHang.setMaxWidth(75);
            columnMaHang.setMinWidth(75);
            
            TableColumn columnThanhTien = columnModel.getColumn(5);
            // Set the preferred width of the column
            columnThanhTien.setMaxWidth(125);
            columnThanhTien.setMinWidth(125);
        
        } catch (SQLException ex) {
            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel239 = new javax.swing.JPanel();
        btnHoaDonChiTiet_capNhat = new javax.swing.JButton();
        btnHoaDonChiTiet_traHang = new javax.swing.JButton();
        jPanel240 = new javax.swing.JPanel();
        jLabel285 = new javax.swing.JLabel();
        lbHoaDonChiTiet_giamGia = new javax.swing.JLabel();
        btnHoaDonChiTiet_in = new javax.swing.JButton();
        btnHoaDonChiTiet_huyBo = new javax.swing.JButton();
        jPanel241 = new javax.swing.JPanel();
        jLabel287 = new javax.swing.JLabel();
        lbHoaDonChiTiet_maHoaDon = new javax.swing.JLabel();
        jPanel244 = new javax.swing.JPanel();
        jLabel293 = new javax.swing.JLabel();
        lbHoaDonChiTiet_tienCanTra = new javax.swing.JLabel();
        jPanel245 = new javax.swing.JPanel();
        lbHoaDonChiTiet_doiTac = new javax.swing.JLabel();
        lbHoaDonChiTiet_tenDoiTac = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbHoaDonChiTiet_danhSachHoaDonChiTiet = new javax.swing.JTable();
        jPanel247 = new javax.swing.JPanel();
        jLabel299 = new javax.swing.JLabel();
        lbHoaDonChiTiet_tienDaTra = new javax.swing.JLabel();
        jPanel248 = new javax.swing.JPanel();
        jLabel301 = new javax.swing.JLabel();
        lbHoaDonChiTiet_thoiGian = new javax.swing.JLabel();
        jPanel250 = new javax.swing.JPanel();
        jLabel305 = new javax.swing.JLabel();
        lbHoaDonChiTiet_soLuong = new javax.swing.JLabel();
        jPanel251 = new javax.swing.JPanel();
        jLabel307 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tareHoaDonChiTiet_ghiChu = new javax.swing.JTextArea();
        jPanel249 = new javax.swing.JPanel();
        jLabel303 = new javax.swing.JLabel();
        lbHoaDonChiTiet_trangThai = new javax.swing.JLabel();
        jPanel246 = new javax.swing.JPanel();
        jLabel297 = new javax.swing.JLabel();
        lbHoaDonChiTiet_chiNhanh = new javax.swing.JLabel();
        jPanel243 = new javax.swing.JPanel();
        jLabel291 = new javax.swing.JLabel();
        lbHoaDonChiTiet_nguoiTao = new javax.swing.JLabel();
        jPanel252 = new javax.swing.JPanel();
        jLabel304 = new javax.swing.JLabel();
        lbHoaDonChiTiet_tienThua = new javax.swing.JLabel();
        jPanel253 = new javax.swing.JPanel();
        jLabel294 = new javax.swing.JLabel();
        lbHoaDonChiTiet_tongTienHang = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel239.setBackground(new java.awt.Color(255, 255, 255));

        btnHoaDonChiTiet_capNhat.setBackground(new java.awt.Color(51, 204, 0));
        btnHoaDonChiTiet_capNhat.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnHoaDonChiTiet_capNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDonChiTiet_capNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        btnHoaDonChiTiet_capNhat.setText("Cập nhật");

        btnHoaDonChiTiet_traHang.setBackground(new java.awt.Color(51, 204, 0));
        btnHoaDonChiTiet_traHang.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnHoaDonChiTiet_traHang.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDonChiTiet_traHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/return2.png"))); // NOI18N
        btnHoaDonChiTiet_traHang.setText("Trả hàng");

        jPanel240.setBackground(new java.awt.Color(255, 255, 255));

        jLabel285.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel285.setText("Giảm giá:");

        lbHoaDonChiTiet_giamGia.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lbHoaDonChiTiet_giamGia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_giamGia.setText("0");

        javax.swing.GroupLayout jPanel240Layout = new javax.swing.GroupLayout(jPanel240);
        jPanel240.setLayout(jPanel240Layout);
        jPanel240Layout.setHorizontalGroup(
            jPanel240Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel240Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel285)
                .addGap(18, 18, 18)
                .addComponent(lbHoaDonChiTiet_giamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel240Layout.setVerticalGroup(
            jPanel240Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel240Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel240Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel285)
                    .addComponent(lbHoaDonChiTiet_giamGia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnHoaDonChiTiet_in.setBackground(new java.awt.Color(51, 204, 0));
        btnHoaDonChiTiet_in.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnHoaDonChiTiet_in.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDonChiTiet_in.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/exportFile.png"))); // NOI18N
        btnHoaDonChiTiet_in.setText("In");

        btnHoaDonChiTiet_huyBo.setBackground(new java.awt.Color(255, 51, 51));
        btnHoaDonChiTiet_huyBo.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnHoaDonChiTiet_huyBo.setForeground(new java.awt.Color(255, 255, 255));
        btnHoaDonChiTiet_huyBo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel.png"))); // NOI18N
        btnHoaDonChiTiet_huyBo.setText("Hủy bỏ");
        btnHoaDonChiTiet_huyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonChiTiet_huyBoActionPerformed(evt);
            }
        });

        jPanel241.setBackground(new java.awt.Color(255, 255, 255));

        jLabel287.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel287.setText("Mã hóa đơn:");

        lbHoaDonChiTiet_maHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lbHoaDonChiTiet_maHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_maHoaDon.setText("0");

        javax.swing.GroupLayout jPanel241Layout = new javax.swing.GroupLayout(jPanel241);
        jPanel241.setLayout(jPanel241Layout);
        jPanel241Layout.setHorizontalGroup(
            jPanel241Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel241Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel287)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoaDonChiTiet_maHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel241Layout.setVerticalGroup(
            jPanel241Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel241Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel241Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel287)
                    .addComponent(lbHoaDonChiTiet_maHoaDon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel244.setBackground(new java.awt.Color(255, 255, 255));

        jLabel293.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel293.setText("Tiền cần trả:");

        lbHoaDonChiTiet_tienCanTra.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lbHoaDonChiTiet_tienCanTra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_tienCanTra.setText("0");

        javax.swing.GroupLayout jPanel244Layout = new javax.swing.GroupLayout(jPanel244);
        jPanel244.setLayout(jPanel244Layout);
        jPanel244Layout.setHorizontalGroup(
            jPanel244Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel244Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel293)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoaDonChiTiet_tienCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel244Layout.setVerticalGroup(
            jPanel244Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel244Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel244Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel293)
                    .addComponent(lbHoaDonChiTiet_tienCanTra))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel245.setBackground(new java.awt.Color(255, 255, 255));

        lbHoaDonChiTiet_doiTac.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbHoaDonChiTiet_doiTac.setText("Khách hàng:");

        lbHoaDonChiTiet_tenDoiTac.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbHoaDonChiTiet_tenDoiTac.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_tenDoiTac.setText("name");

        javax.swing.GroupLayout jPanel245Layout = new javax.swing.GroupLayout(jPanel245);
        jPanel245.setLayout(jPanel245Layout);
        jPanel245Layout.setHorizontalGroup(
            jPanel245Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel245Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbHoaDonChiTiet_doiTac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoaDonChiTiet_tenDoiTac, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel245Layout.setVerticalGroup(
            jPanel245Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel245Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel245Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoaDonChiTiet_doiTac)
                    .addComponent(lbHoaDonChiTiet_tenDoiTac))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbHoaDonChiTiet_danhSachHoaDonChiTiet.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tbHoaDonChiTiet_danhSachHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hàng", "Tên hàng", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền"
            }
        ));
        tbHoaDonChiTiet_danhSachHoaDonChiTiet.setRowHeight(30);
        jScrollPane15.setViewportView(tbHoaDonChiTiet_danhSachHoaDonChiTiet);

        jPanel247.setBackground(new java.awt.Color(255, 255, 255));

        jLabel299.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel299.setText("Tiền đã trả");

        lbHoaDonChiTiet_tienDaTra.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lbHoaDonChiTiet_tienDaTra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_tienDaTra.setText("0");

        javax.swing.GroupLayout jPanel247Layout = new javax.swing.GroupLayout(jPanel247);
        jPanel247.setLayout(jPanel247Layout);
        jPanel247Layout.setHorizontalGroup(
            jPanel247Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel247Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel299)
                .addGap(24, 24, 24)
                .addComponent(lbHoaDonChiTiet_tienDaTra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel247Layout.setVerticalGroup(
            jPanel247Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel247Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel247Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel299)
                    .addComponent(lbHoaDonChiTiet_tienDaTra))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel248.setBackground(new java.awt.Color(255, 255, 255));

        jLabel301.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel301.setText("Thời gian:");

        lbHoaDonChiTiet_thoiGian.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbHoaDonChiTiet_thoiGian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_thoiGian.setText("0");

        javax.swing.GroupLayout jPanel248Layout = new javax.swing.GroupLayout(jPanel248);
        jPanel248.setLayout(jPanel248Layout);
        jPanel248Layout.setHorizontalGroup(
            jPanel248Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel248Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel301)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoaDonChiTiet_thoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel248Layout.setVerticalGroup(
            jPanel248Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel248Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel248Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel301)
                    .addComponent(lbHoaDonChiTiet_thoiGian))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel250.setBackground(new java.awt.Color(255, 255, 255));

        jLabel305.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel305.setText("Tổng số lượng:");

        lbHoaDonChiTiet_soLuong.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lbHoaDonChiTiet_soLuong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_soLuong.setText("0");

        javax.swing.GroupLayout jPanel250Layout = new javax.swing.GroupLayout(jPanel250);
        jPanel250.setLayout(jPanel250Layout);
        jPanel250Layout.setHorizontalGroup(
            jPanel250Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel250Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel305)
                .addGap(18, 18, 18)
                .addComponent(lbHoaDonChiTiet_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel250Layout.setVerticalGroup(
            jPanel250Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel250Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel250Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel305)
                    .addComponent(lbHoaDonChiTiet_soLuong))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel251.setBackground(new java.awt.Color(255, 255, 255));

        jLabel307.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel307.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pencil.png"))); // NOI18N

        tareHoaDonChiTiet_ghiChu.setColumns(20);
        tareHoaDonChiTiet_ghiChu.setFont(new java.awt.Font("Times New Roman", 2, 16)); // NOI18N
        tareHoaDonChiTiet_ghiChu.setRows(5);
        tareHoaDonChiTiet_ghiChu.setText("Ghi chú...");
        jScrollPane16.setViewportView(tareHoaDonChiTiet_ghiChu);

        jPanel249.setBackground(new java.awt.Color(255, 255, 255));

        jLabel303.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel303.setText("Trạng thái:");

        lbHoaDonChiTiet_trangThai.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbHoaDonChiTiet_trangThai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_trangThai.setText("0");

        javax.swing.GroupLayout jPanel249Layout = new javax.swing.GroupLayout(jPanel249);
        jPanel249.setLayout(jPanel249Layout);
        jPanel249Layout.setHorizontalGroup(
            jPanel249Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel249Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel303)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbHoaDonChiTiet_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel249Layout.setVerticalGroup(
            jPanel249Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel249Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel249Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel303)
                    .addComponent(lbHoaDonChiTiet_trangThai))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel246.setBackground(new java.awt.Color(255, 255, 255));

        jLabel297.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel297.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel297.setText("Chi nhánh:");

        lbHoaDonChiTiet_chiNhanh.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbHoaDonChiTiet_chiNhanh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_chiNhanh.setText("0");

        javax.swing.GroupLayout jPanel246Layout = new javax.swing.GroupLayout(jPanel246);
        jPanel246.setLayout(jPanel246Layout);
        jPanel246Layout.setHorizontalGroup(
            jPanel246Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel246Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel297)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbHoaDonChiTiet_chiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel246Layout.setVerticalGroup(
            jPanel246Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel246Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel246Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel297)
                    .addComponent(lbHoaDonChiTiet_chiNhanh))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel243.setBackground(new java.awt.Color(255, 255, 255));

        jLabel291.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel291.setText("Người tạo:");

        lbHoaDonChiTiet_nguoiTao.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbHoaDonChiTiet_nguoiTao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_nguoiTao.setText("0");

        javax.swing.GroupLayout jPanel243Layout = new javax.swing.GroupLayout(jPanel243);
        jPanel243.setLayout(jPanel243Layout);
        jPanel243Layout.setHorizontalGroup(
            jPanel243Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel243Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel291)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(lbHoaDonChiTiet_nguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel243Layout.setVerticalGroup(
            jPanel243Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel243Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel243Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel291)
                    .addComponent(lbHoaDonChiTiet_nguoiTao))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel251Layout = new javax.swing.GroupLayout(jPanel251);
        jPanel251.setLayout(jPanel251Layout);
        jPanel251Layout.setHorizontalGroup(
            jPanel251Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel251Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel251Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel249, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel246, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel243, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel307)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel251Layout.setVerticalGroup(
            jPanel251Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16)
            .addGroup(jPanel251Layout.createSequentialGroup()
                .addComponent(jLabel307)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel251Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel249, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jPanel246, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel243, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel252.setBackground(new java.awt.Color(255, 255, 255));

        jLabel304.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel304.setText("Tiền thừa:");

        lbHoaDonChiTiet_tienThua.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lbHoaDonChiTiet_tienThua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_tienThua.setText("0");

        javax.swing.GroupLayout jPanel252Layout = new javax.swing.GroupLayout(jPanel252);
        jPanel252.setLayout(jPanel252Layout);
        jPanel252Layout.setHorizontalGroup(
            jPanel252Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel252Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel304)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lbHoaDonChiTiet_tienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel252Layout.setVerticalGroup(
            jPanel252Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel252Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel252Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel304)
                    .addComponent(lbHoaDonChiTiet_tienThua))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel253.setBackground(new java.awt.Color(255, 255, 255));

        jLabel294.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel294.setText("Tổng tiền hàng:");

        lbHoaDonChiTiet_tongTienHang.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lbHoaDonChiTiet_tongTienHang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoaDonChiTiet_tongTienHang.setText("0");

        javax.swing.GroupLayout jPanel253Layout = new javax.swing.GroupLayout(jPanel253);
        jPanel253.setLayout(jPanel253Layout);
        jPanel253Layout.setHorizontalGroup(
            jPanel253Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel253Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel294, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoaDonChiTiet_tongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel253Layout.setVerticalGroup(
            jPanel253Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel253Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel253Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel294)
                    .addComponent(lbHoaDonChiTiet_tongTienHang))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel239Layout = new javax.swing.GroupLayout(jPanel239);
        jPanel239.setLayout(jPanel239Layout);
        jPanel239Layout.setHorizontalGroup(
            jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel239Layout.createSequentialGroup()
                .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel239Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane15)
                            .addGroup(jPanel239Layout.createSequentialGroup()
                                .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel245, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel248, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel241, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel251, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel239Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel239Layout.createSequentialGroup()
                                .addComponent(btnHoaDonChiTiet_capNhat)
                                .addGap(18, 18, 18)
                                .addComponent(btnHoaDonChiTiet_traHang, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnHoaDonChiTiet_in)
                                .addGap(18, 18, 18)
                                .addComponent(btnHoaDonChiTiet_huyBo))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel239Layout.createSequentialGroup()
                                .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel250, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel240, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel253, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel239Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jPanel247, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel252, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel239Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jPanel244, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(11, 11, 11)))))
                .addContainerGap())
        );
        jPanel239Layout.setVerticalGroup(
            jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel239Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel239Layout.createSequentialGroup()
                        .addComponent(jPanel241, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel248, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jPanel245, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel251, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel239Layout.createSequentialGroup()
                        .addComponent(jPanel250, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel253, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel239Layout.createSequentialGroup()
                        .addComponent(jPanel244, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel247, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel252, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel240, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel239Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHoaDonChiTiet_capNhat)
                    .addComponent(btnHoaDonChiTiet_traHang)
                    .addComponent(btnHoaDonChiTiet_in)
                    .addComponent(btnHoaDonChiTiet_huyBo))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel239, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel239, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHoaDonChiTiet_huyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonChiTiet_huyBoActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHoaDonChiTiet_huyBoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame_HoaDonChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_HoaDonChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_HoaDonChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_HoaDonChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_HoaDonChiTiet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoaDonChiTiet_capNhat;
    private javax.swing.JButton btnHoaDonChiTiet_huyBo;
    private javax.swing.JButton btnHoaDonChiTiet_in;
    private javax.swing.JButton btnHoaDonChiTiet_traHang;
    private javax.swing.JLabel jLabel285;
    private javax.swing.JLabel jLabel287;
    private javax.swing.JLabel jLabel291;
    private javax.swing.JLabel jLabel293;
    private javax.swing.JLabel jLabel294;
    private javax.swing.JLabel jLabel297;
    private javax.swing.JLabel jLabel299;
    private javax.swing.JLabel jLabel301;
    private javax.swing.JLabel jLabel303;
    private javax.swing.JLabel jLabel304;
    private javax.swing.JLabel jLabel305;
    private javax.swing.JLabel jLabel307;
    private javax.swing.JPanel jPanel239;
    private javax.swing.JPanel jPanel240;
    private javax.swing.JPanel jPanel241;
    private javax.swing.JPanel jPanel243;
    private javax.swing.JPanel jPanel244;
    private javax.swing.JPanel jPanel245;
    private javax.swing.JPanel jPanel246;
    private javax.swing.JPanel jPanel247;
    private javax.swing.JPanel jPanel248;
    private javax.swing.JPanel jPanel249;
    private javax.swing.JPanel jPanel250;
    private javax.swing.JPanel jPanel251;
    private javax.swing.JPanel jPanel252;
    private javax.swing.JPanel jPanel253;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JLabel lbHoaDonChiTiet_chiNhanh;
    private javax.swing.JLabel lbHoaDonChiTiet_doiTac;
    private javax.swing.JLabel lbHoaDonChiTiet_giamGia;
    private javax.swing.JLabel lbHoaDonChiTiet_maHoaDon;
    private javax.swing.JLabel lbHoaDonChiTiet_nguoiTao;
    private javax.swing.JLabel lbHoaDonChiTiet_soLuong;
    private javax.swing.JLabel lbHoaDonChiTiet_tenDoiTac;
    private javax.swing.JLabel lbHoaDonChiTiet_thoiGian;
    private javax.swing.JLabel lbHoaDonChiTiet_tienCanTra;
    private javax.swing.JLabel lbHoaDonChiTiet_tienDaTra;
    private javax.swing.JLabel lbHoaDonChiTiet_tienThua;
    private javax.swing.JLabel lbHoaDonChiTiet_tongTienHang;
    private javax.swing.JLabel lbHoaDonChiTiet_trangThai;
    private javax.swing.JTextArea tareHoaDonChiTiet_ghiChu;
    private javax.swing.JTable tbHoaDonChiTiet_danhSachHoaDonChiTiet;
    // End of variables declaration//GEN-END:variables
}
