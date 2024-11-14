package form;

import component.ChoNgoiItem;
import component.DataSearch;
import component.FormListDontreo;
import component.FormToaGhe;
import component.FormToaNam;
import component.IconToa;
import component.PanelSearch;
import component.TauItem;
import component.jFrameMuaVe;
import dao.ChiTietVeDao;
import dao.ChoNgoiDao;
import dao.ChuyenDao;
import dao.GaDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.KhuyenMaiDao;
import dao.ToaDao;
import dao.TuyenDao;
import dao.VeDao;
import entity.ChiTietVe;
import entity.ChoNgoi;
import entity.Chuyen;
import entity.Ga;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.TaiKhoan;
import entity.Toa;
import entity.Ve;
import event.EvenItemGaClick;
import event.EvenItemTau;
import event.EventItemToa;
import jakarta.persistence.EntityManagerFactory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import model.Model_InfoVe;
import model.Model_Tau;
import swing.ScrollBar;
import swing.ScrollBar2;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FormChonTau extends javax.swing.JPanel {

	public EvenItemTau getEvent() {
		return eventTau;
	}

	public void setEvent(EvenItemTau event) {
		this.eventTau = event;
	}

	public void setEventToa(EventItemToa eventToa) {
		this.eventToa = eventToa;
	}

	public EventItemToa getEventToa() {
		return eventToa;
	}

	private Ga gaDau;
	private Ga gaCuoi;
	private EntityManagerFactory emf;
	private EventItemToa eventToa;
	private EvenItemTau eventTau;
	private MainForm main;
	private FormToaGhe formGhe;
	private FormToaNam formNam;
	private FormListDontreo formDonTreo;
	private List<Chuyen> listChuyens;
	private Ga gaDi;
	private Ga gaDen;
	private LocalDate ngayDi;
	private ChiTietVeDao chiTietVeDao;
	private LocalDate ngayVe;
	private boolean isMotChieu;
	private GaDao gaDao;
	private VeDao veDao;
	private KhuyenMaiDao kmDao;
	private HoaDonDao hoaDonDao;
	private KhachHangDao khachHangDao;
	private JPopupMenu menu;
	private PanelSearch search;
	private List<Ga> listGas;
	private ToaDao toaDao;
	private ChoNgoiDao choNgoiDao;
	private jFrameMuaVe frameMuaVe;
	private Map<String, Set<ChoNgoi>> listChoChon;
	private List<Model_InfoVe> listInfoVes;
	DefaultTableModel model;
	private boolean isJrVe = false;
	private boolean isJrDi = true;
	private JComboBox<String> cbDT;
	private Map<String, Set<String>> liscccd;
	private TaiKhoan taiKhoan;
	private HoaDon hoaDon;

	public FormChonTau(EntityManagerFactory emf, MainForm main, List<Chuyen> listChuyens, Ga gaDi, Ga gaDen,
			LocalDate ngayDi, LocalDate ngayVe, boolean isMotChieu, TaiKhoan taiKhoan) {
		this.emf = emf;
		this.main = main;
		this.taiKhoan = taiKhoan;
		this.listChuyens = listChuyens;
		this.gaDi = gaDi;
		this.gaDen = gaDen;
		this.ngayDi = ngayDi;
		this.ngayVe = ngayVe;
		this.isMotChieu = isMotChieu;
		this.chiTietVeDao = new ChiTietVeDao(emf);
		this.toaDao = new ToaDao(emf);
		this.gaDao = new GaDao(emf);
		this.veDao = new VeDao(emf);
		this.choNgoiDao = new ChoNgoiDao(emf);
		this.kmDao = new KhuyenMaiDao(emf);
		this.hoaDonDao = new HoaDonDao(emf);
		this.khachHangDao = new KhachHangDao(emf);
		this.listGas = gaDao.getAllGa();
		this.gaDau = gaDao.layGaDau();
		this.gaCuoi = gaDao.layGaCuoi();
		this.listChoChon = new HashMap<String, Set<ChoNgoi>>();
		this.liscccd = new HashMap<String, Set<String>>();
		this.listInfoVes = new ArrayList<Model_InfoVe>();
		initComponents();
		
		btnTimChuyen.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
		btnTimChuyen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search2.png"))); // NOI18N
		
		btnHuyCho.setBackground(new java.awt.Color(231, 55, 55));
		btnHuyCho.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
		btnHuyCho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
		
		btnXacNhan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
		btnXacNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check.png"))); // NOI18N
		
		btnXuLyTreo.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
		btnXuLyTreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xuly.png"))); // NOI18N
		
		model = (DefaultTableModel) tbListVe.getModel();
		cbDT = new JComboBox<String>();
		cbDT.addItem("Người lớn");
		cbDT.addItem("Trẻ em");
		cbDT.addItem("Sinh viên");
		cbDT.addItem("Người già");
		TableColumn col = tbListVe.getColumnModel().getColumn(2);
		cbDT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cbDTAction(e);
			}
		});

		col.setCellEditor(new DefaultCellEditor(cbDT));
		jpIfHanhTrinh.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Thông tin hành trình", 0, HEIGHT, new Font(Font.SANS_SERIF, Font.BOLD, 20) {
				}, Color.black));
		jpIfHanhTrinh.setBackground(Color.white);
		jpIfHanhKhach.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Thông tin khách hàng", 0, HEIGHT, new Font(Font.SANS_SERIF, Font.BOLD, 20) {
				}, Color.black));
		jpChieu.setBackground(Color.white);
		jpChieu.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Chiều mua vé",
				0, HEIGHT, new Font(Font.SANS_SERIF, Font.BOLD, 20) {
				}, Color.black));
		jpIfHanhKhach.setBackground(Color.white);
		jpIfve.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Thông tin vé",
				0, HEIGHT, new Font(Font.SANS_SERIF, Font.BOLD, 20) {
				}, Color.black));
		jpIfve.setBackground(Color.white);
		jpChucNang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Nghiệp vụ",
				0, HEIGHT, new Font(Font.SANS_SERIF, Font.BOLD, 20) {
				}, Color.black));
		jpChucNang.setBackground(Color.white);
		scp.setHorizontalScrollBar(new ScrollBar());
		spListKhoang.setHorizontalScrollBar(new ScrollBar2());
		scpTbVe.setVerticalScrollBar(new ScrollBar());
		tbListVe.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 14));
//        tbListVe.getColumnModel().getColumn(0).setCellRenderer(new TableFieldTextCellRender());
//        tbListVe.getColumnModel().getColumn(0).setCellEditor(new TabelFiledTextEditor());
		tbListVe.getTableHeader().setPreferredSize(new Dimension(30, 30));
		((DefaultTableCellRenderer) tbListVe.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);
		AddDataTau();
		menu = new JPopupMenu();
		search = new PanelSearch();
		menu.setBorder(BorderFactory.createLineBorder(new Color(176, 176, 176)));
		menu.add(search);
		menu.setFocusable(false);
		jrDi.setSelected(true);
		updateDataChuyen();
		jtCccd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				txtCccdReload(e);
			}
		});
	}

//    Du lieu toa
	private void AddDataToa(Model_Tau chuyen) {
		listIconTau.removeAll();
		setEventToa(new EventItemToa() {
			@Override
			public void itemClick(Component com, Toa toa) {
				setSelectedToa(com, toa, chuyen);
			}

		});
		IconToa dau = new IconToa();
		dau.setData(new ImageIcon(getClass().getResource("/icon/trainHead.png")), chuyen.getMaTau());
		listIconTau.add(dau);
		List<Toa> listToas = toaDao.getAllToaByMaChuyen(chuyen.getMaTau());
		for (Toa toa : listToas) {
			addItemToa(toa);
		}
		Component com = listIconTau.getComponent(listToas.get(0).getViTri());
		setSelectedToa(com, listToas.get(0), chuyen);
	}

	private void txtCccdReload(KeyEvent e) {
		String cc = jtCccd.getText();
		KhachHang kh = khachHangDao.getKhachHangByCCCD(cc);
		if (kh != null) {
			jtHoT.setText(kh.getHoTen());
			jtSdt.setText(kh.getSdt());
			jtEm.setText(kh.getEmail());
		}
	}

	public void addItemToa(Toa toa) {
		IconToa icontoa = new IconToa();
		icontoa.setData(toa.getViTri());
		icontoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					eventToa.itemClick(icontoa, toa);
				}
			}

		});
		listIconTau.add(icontoa);
		listIconTau.repaint();
		listIconTau.revalidate();
	}

	public void setSelectedToa(Component item, Toa toa, Model_Tau chuyen) {
		if (((IconToa) item).isSeleted())
			return;
		for (Component com : listIconTau.getComponents()) {
			IconToa i = (IconToa) com;
			if (i.isSeleted()) {
				i.setSeleted(false);
			}
		}
		((IconToa) item).setSeleted(true);
		int index = toa.getViTri();

		if (index < 5) {
			List<ChoNgoi> lisNgois = choNgoiDao.getAllChoNgoiTrongVTToa(gaDi.getId(), gaDen.getId(),
					chuyen.getChuyen().getMaChuyen(), index, true,chuyen.getMaTau());
			spListKhoang.setViewportView(
					formGhe = new FormToaGhe(chuyen, hoaDon, lisNgois, listChoChon, model, listInfoVes));
			lbifToa.setText("Toa " + index + ": Ngồi mền điều hòa");
		} else if (index < 8) {
			List<ChoNgoi> lisNgois = choNgoiDao.getAllChoNgoiTrongVTToa(gaDi.getId(), gaDen.getId(),
					chuyen.getChuyen().getMaChuyen(), index, true,chuyen.getMaTau());
			spListKhoang.setViewportView(
					formNam = new FormToaNam(6, chuyen, hoaDon, lisNgois, listChoChon, model, listInfoVes));
			lbifToa.setText("Toa " + index + ": Giường nằm khoang 6 điều hòa");
		} else {
			List<ChoNgoi> lisNgois = choNgoiDao.getAllChoNgoiTrongVTToa(gaDi.getId(), gaDen.getId(),
					chuyen.getChuyen().getMaChuyen(), index, true,chuyen.getMaTau());
			spListKhoang.setViewportView(
					formNam = new FormToaNam(4, chuyen, hoaDon, lisNgois, listChoChon, model, listInfoVes));
			lbifToa.setText("Toa " + index + ": Giường nằm khoang 4 điều hòa");
		}
	}

//    Du lieu tau
	private void AddDataTau() {
		setEvent(new EvenItemTau() {
			@Override
			public void itemClick(Component com, Model_Tau item) {
				if (setSelectedTau(com))
					AddDataToa(item);
			}

		});
		for (Chuyen chuyen : listChuyens) {
			if (gaDi.getId() < gaDen.getId())
				addItemTau(new Model_Tau(chuyen, gaDi, gaDen, gaDau));
			else
				addItemTau(new Model_Tau(chuyen, gaDi, gaDen, gaCuoi));
		}
		Component com = listTau.getComponent(0);
		((TauItem) com).setSeleted(true);
		AddDataToa(((TauItem) com).getData());

	}

	public void addItemTau(Model_Tau data) {
		TauItem item = new TauItem();
		item.setData(data);
		item.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					eventTau.itemClick(item, data);
				}
			}

		});
		listTau.add(item);
		listTau.repaint();
		listTau.revalidate();
	}

	public boolean setSelectedTau(Component item) {
		if (((TauItem) item).isSeleted())
			return false;
		for (Component com : listTau.getComponents()) {
			TauItem i = (TauItem) com;
			if (i.isSeleted())
				i.setSeleted(false);

		}
		((TauItem) item).setSeleted(true);
		return true;
	}

	private void updateDataChuyen() {
		jtGaDi.setText(gaDi.getTenGa());
		jtGaDen.setText(gaDen.getTenGa());
		if (isMotChieu) {
			rdMotChieu.setSelected(true);
			jrVe.setEnabled(false);
			jrDi.setSelected(true);
			dateVe.setEnabled(false);
		} else {
			rdHoiKhu.setSelected(true);
			jrVe.setEnabled(true);
			dateVe.setEnabled(true);
		}
		dateDi.setDate(Date.from(ngayDi.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		if (ngayVe != null)
			dateVe.setDate(Date.from(ngayVe.atStartOfDay(ZoneId.systemDefault()).toInstant()));

	}

	private List<DataSearch> search(String text) {
		int limitData = 4;
		List<DataSearch> list = new ArrayList<DataSearch>();
		if (text.equalsIgnoreCase("")) {
			return list;
		}
		for (Ga a : listGas) {
			if (a.getTenGa().toLowerCase().contains(text)) {
				list.add(new DataSearch(a.getTenGa()));
				if (list.size() == limitData) {
					break;
				}
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		btnGroup = new javax.swing.ButtonGroup();
		groupDiVe = new javax.swing.ButtonGroup();
		scp = new javax.swing.JScrollPane();
		listTau = new component.ListTau();
		listIconTau = new component.ListIconTau();
		formIfToa1 = new component.FormIfToa();
		jpIfToa = new javax.swing.JPanel();
		spListKhoang = new javax.swing.JScrollPane();
		lbNext = new javax.swing.JLabel();
		formTabelVe = new component.FormTabelVe();
		scpTbVe = new javax.swing.JScrollPane();
		tbListVe = new javax.swing.JTable();
		btnHuyCho = new javax.swing.JButton();
		jpIfHanhTrinh = new javax.swing.JPanel();
		lbGaDi = new javax.swing.JLabel();
		lbGaDen = new javax.swing.JLabel();
		rdMotChieu = new javax.swing.JRadioButton();
		rdHoiKhu = new javax.swing.JRadioButton();
		jtGaDi = new javax.swing.JTextField();
		jtGaDen = new javax.swing.JTextField();
		lbNgayDi = new javax.swing.JLabel();
		dateDi = new com.toedter.calendar.JDateChooser();
		lbVe = new javax.swing.JLabel();
		dateVe = new com.toedter.calendar.JDateChooser();
		btnTimChuyen = new javax.swing.JButton();
		jpIfHanhKhach = new javax.swing.JPanel();
		lbGaDi1 = new javax.swing.JLabel();
		jtCccd = new javax.swing.JTextField();
		lbGaDi2 = new javax.swing.JLabel();
		jtHoT = new javax.swing.JTextField();
		lbGaDi3 = new javax.swing.JLabel();
		lbGaDi4 = new javax.swing.JLabel();
		jtSdt = new javax.swing.JTextField();
		jtEm = new javax.swing.JTextField();
		jpChucNang = new javax.swing.JPanel();
		btnXuLyTreo = new javax.swing.JButton();
		btnXacNhan = new javax.swing.JButton();
		jpIfve = new javax.swing.JPanel();
		lbTauChuyen = new javax.swing.JLabel();
		lbThoiGianLen = new javax.swing.JLabel();
		lbToaCho = new javax.swing.JLabel();
		lbMoTaVe = new javax.swing.JLabel();
		jpChieu = new javax.swing.JPanel();
		jrDi = new javax.swing.JRadioButton();
		jrVe = new javax.swing.JRadioButton();

		scp.setBorder(null);
		scp.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		listTau.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));
		scp.setViewportView(listTau);

		jpIfToa.setOpaque(false);

		spListKhoang.setBackground(new java.awt.Color(204, 204, 204));
		spListKhoang.setBorder(null);
		spListKhoang.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		lbNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/next.png")));
		lbifToa = new javax.swing.JLabel();
		
				lbifToa.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
				lbifToa.setText("Toa 2: Ngồi mền điều hòa");

		javax.swing.GroupLayout formIfToa1Layout = new javax.swing.GroupLayout(formIfToa1);
		formIfToa1Layout.setHorizontalGroup(
			formIfToa1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(formIfToa1Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(formIfToa1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(formIfToa1Layout.createSequentialGroup()
							.addComponent(spListKhoang, GroupLayout.PREFERRED_SIZE, 772, GroupLayout.PREFERRED_SIZE)
							.addGap(1585)
							.addGroup(formIfToa1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbNext)
								.addComponent(jpIfToa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lbifToa, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		formIfToa1Layout.setVerticalGroup(
			formIfToa1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(formIfToa1Layout.createSequentialGroup()
					.addGap(26)
					.addGroup(formIfToa1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(formIfToa1Layout.createSequentialGroup()
							.addGap(133)
							.addComponent(lbNext))
						.addGroup(formIfToa1Layout.createSequentialGroup()
							.addGap(32)
							.addComponent(jpIfToa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(61))
				.addGroup(formIfToa1Layout.createSequentialGroup()
					.addComponent(lbifToa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spListKhoang, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
					.addContainerGap())
		);
		formIfToa1.setLayout(formIfToa1Layout);

		tbListVe.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		tbListVe.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "CCCD", "Họ tên", "Đối tượng", "Thông tin vé", "Giá vé", "Giảm đối tương", "Thành tiền" }) {
			boolean[] canEdit = new boolean[] { true, true, true, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tbListVe.setOpaque(false);
		tbListVe.setRowHeight(30);
		tbListVe.setSelectionBackground(new java.awt.Color(204, 204, 204));
		tbListVe.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbListVeMouseClicked(evt);
			}
		});
		scpTbVe.setViewportView(tbListVe);

		btnHuyCho.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
		btnHuyCho.setText("Hủy chổ");
		btnHuyCho.setPreferredSize(new java.awt.Dimension(96, 55));
		btnHuyCho.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnHuyChoMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout formTabelVeLayout = new javax.swing.GroupLayout(formTabelVe);
		formTabelVeLayout.setHorizontalGroup(
			formTabelVeLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(formTabelVeLayout.createSequentialGroup()
					.addGroup(formTabelVeLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(formTabelVeLayout.createSequentialGroup()
							.addGap(163)
							.addComponent(btnHuyCho, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
						.addComponent(scpTbVe, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
					.addContainerGap())
		);
		formTabelVeLayout.setVerticalGroup(
			formTabelVeLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(formTabelVeLayout.createSequentialGroup()
					.addComponent(scpTbVe, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnHuyCho, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(13))
		);
		formTabelVe.setLayout(formTabelVeLayout);

		lbGaDi.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		lbGaDi.setText("Ga đi");

		lbGaDen.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		lbGaDen.setText("Ga đến");

		btnGroup.add(rdMotChieu);
		rdMotChieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		rdMotChieu.setText("Một chiều");
		rdMotChieu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				rdMotChieuMouseClicked(evt);
			}
		});
		rdMotChieu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdMotChieuActionPerformed(evt);
			}
		});

		btnGroup.add(rdHoiKhu);
		rdHoiKhu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		rdHoiKhu.setText("Khứ hồi");
		rdHoiKhu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				rdHoiKhuMouseClicked(evt);
			}
		});

		jtGaDi.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		jtGaDi.setMinimumSize(new java.awt.Dimension(64, 40));
		jtGaDi.setPreferredSize(new java.awt.Dimension(64, 40));
		jtGaDi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jtGaDiMouseClicked(evt);
			}
		});
		jtGaDi.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				jtGaDiKeyReleased(evt);
			}
		});

		jtGaDen.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		jtGaDen.setMinimumSize(new java.awt.Dimension(64, 40));
		jtGaDen.setPreferredSize(new java.awt.Dimension(64, 40));
		jtGaDen.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jtGaDenMouseClicked(evt);
			}
		});
		jtGaDen.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				jtGaDenKeyReleased(evt);
			}
		});

		lbNgayDi.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		lbNgayDi.setText("Ngày về");

		dateDi.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		dateDi.setPreferredSize(new java.awt.Dimension(88, 40));

		lbVe.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		lbVe.setText("Ngày đi");

		dateVe.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		dateVe.setPreferredSize(new java.awt.Dimension(88, 40));

		btnTimChuyen.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
		btnTimChuyen.setText("Tìm");
		btnTimChuyen.setBorder(null);
		btnTimChuyen.setPreferredSize(new java.awt.Dimension(75, 55));
		btnTimChuyen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTimChuyenActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jpIfHanhTrinhLayout = new javax.swing.GroupLayout(jpIfHanhTrinh);
		jpIfHanhTrinhLayout.setHorizontalGroup(
			jpIfHanhTrinhLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jpIfHanhTrinhLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jpIfHanhTrinhLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, jpIfHanhTrinhLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(rdMotChieu)
							.addGap(21)
							.addComponent(rdHoiKhu))
						.addGroup(Alignment.LEADING, jpIfHanhTrinhLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, jpIfHanhTrinhLayout.createSequentialGroup()
								.addComponent(lbGaDen)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jtGaDen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, jpIfHanhTrinhLayout.createSequentialGroup()
								.addComponent(lbGaDi)
								.addGap(18)
								.addComponent(jtGaDi, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.LEADING, jpIfHanhTrinhLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(Alignment.LEADING, jpIfHanhTrinhLayout.createSequentialGroup()
								.addComponent(lbNgayDi)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(dateVe, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, jpIfHanhTrinhLayout.createSequentialGroup()
								.addComponent(lbVe)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(dateDi, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))))
					.addContainerGap())
				.addGroup(Alignment.LEADING, jpIfHanhTrinhLayout.createSequentialGroup()
					.addGap(56)
					.addComponent(btnTimChuyen, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(109, Short.MAX_VALUE))
		);
		jpIfHanhTrinhLayout.setVerticalGroup(
			jpIfHanhTrinhLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jpIfHanhTrinhLayout.createSequentialGroup()
					.addGap(0)
					.addGroup(jpIfHanhTrinhLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbGaDi)
						.addComponent(jtGaDi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(jpIfHanhTrinhLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbGaDen)
						.addComponent(jtGaDen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(3)
					.addGroup(jpIfHanhTrinhLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdMotChieu)
						.addComponent(rdHoiKhu))
					.addGap(18)
					.addGroup(jpIfHanhTrinhLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(jpIfHanhTrinhLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(dateDi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(dateVe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(jpIfHanhTrinhLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(lbVe)
							.addGap(25)
							.addComponent(lbNgayDi)))
					.addGap(18)
					.addComponent(btnTimChuyen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(198))
		);
		jpIfHanhTrinh.setLayout(jpIfHanhTrinhLayout);

		lbGaDi1.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		lbGaDi1.setText("CCCD");

		jtCccd.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		jtCccd.setMinimumSize(new java.awt.Dimension(64, 40));
		jtCccd.setPreferredSize(new java.awt.Dimension(64, 40));

		lbGaDi2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		lbGaDi2.setText("Họ tên");

		jtHoT.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		jtHoT.setMinimumSize(new java.awt.Dimension(64, 40));
		jtHoT.setPreferredSize(new java.awt.Dimension(64, 40));

		lbGaDi3.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		lbGaDi3.setText("Số điện thoại");

		lbGaDi4.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		lbGaDi4.setText("Email");

		jtSdt.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		jtSdt.setMinimumSize(new java.awt.Dimension(64, 40));
		jtSdt.setPreferredSize(new java.awt.Dimension(64, 40));

		jtEm.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		jtEm.setMinimumSize(new java.awt.Dimension(64, 40));
		jtEm.setPreferredSize(new java.awt.Dimension(64, 40));

		javax.swing.GroupLayout jpIfHanhKhachLayout = new javax.swing.GroupLayout(jpIfHanhKhach);
		jpIfHanhKhachLayout.setHorizontalGroup(
			jpIfHanhKhachLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jpIfHanhKhachLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(jpIfHanhKhachLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(jpIfHanhKhachLayout.createSequentialGroup()
							.addComponent(lbGaDi4)
							.addGap(0, 263, Short.MAX_VALUE))
						.addGroup(jpIfHanhKhachLayout.createSequentialGroup()
							.addGroup(jpIfHanhKhachLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbGaDi2)
								.addComponent(lbGaDi1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(jpIfHanhKhachLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jtHoT, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addComponent(jtCccd, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))
						.addGroup(jpIfHanhKhachLayout.createSequentialGroup()
							.addComponent(lbGaDi3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(jpIfHanhKhachLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jtEm, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
								.addComponent(jtSdt, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))))
					.addGap(10))
		);
		jpIfHanhKhachLayout.setVerticalGroup(
			jpIfHanhKhachLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jpIfHanhKhachLayout.createSequentialGroup()
					.addGap(0)
					.addGroup(jpIfHanhKhachLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbGaDi1)
						.addComponent(jtCccd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(jpIfHanhKhachLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbGaDi2)
						.addComponent(jtHoT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(jpIfHanhKhachLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbGaDi3)
						.addComponent(jtSdt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(jpIfHanhKhachLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbGaDi4)
						.addComponent(jtEm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		jpIfHanhKhach.setLayout(jpIfHanhKhachLayout);

		btnXuLyTreo.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
		btnXuLyTreo.setText("Xử lý đơn tạm");
		btnXuLyTreo.setBorder(null);
		btnXuLyTreo.setMinimumSize(new java.awt.Dimension(66, 55));
		btnXuLyTreo.setPreferredSize(new java.awt.Dimension(66, 55));
		btnXuLyTreo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXuLyTreoActionPerformed(evt);
			}
		});

		btnXacNhan.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
		btnXacNhan.setText("Xác nhận mua vé");
		btnXacNhan.setActionCommand("");
		btnXacNhan.setBorder(null);
		btnXacNhan.setMinimumSize(new java.awt.Dimension(66, 55));
		btnXacNhan.setPreferredSize(new java.awt.Dimension(66, 55));
		btnXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnXacNhanMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jpChucNangLayout = new javax.swing.GroupLayout(jpChucNang);
		jpChucNangLayout.setHorizontalGroup(
			jpChucNangLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, jpChucNangLayout.createSequentialGroup()
					.addGap(130)
					.addComponent(btnXuLyTreo, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(143)
					.addComponent(btnXacNhan, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(173, Short.MAX_VALUE))
		);
		jpChucNangLayout.setVerticalGroup(
			jpChucNangLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jpChucNangLayout.createSequentialGroup()
					.addGroup(jpChucNangLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnXuLyTreo, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXacNhan, GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE))
					.addContainerGap())
		);
		jpChucNang.setLayout(jpChucNangLayout);

		jpIfve.setToolTipText("SE1 Long Khánh-Sài Gòn");
		jpIfve.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

		lbTauChuyen.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		lbTauChuyen.setText(" ");

		lbThoiGianLen.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		lbThoiGianLen.setText(" ");

		lbToaCho.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		lbToaCho.setText(" ");

		lbMoTaVe.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
		lbMoTaVe.setText(" ");

		javax.swing.GroupLayout jpIfveLayout = new javax.swing.GroupLayout(jpIfve);
		jpIfve.setLayout(jpIfveLayout);
		jpIfveLayout.setHorizontalGroup(jpIfveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jpIfveLayout.createSequentialGroup().addGap(17, 17, 17)
						.addGroup(jpIfveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(lbThoiGianLen, javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lbToaCho, javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lbMoTaVe, javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lbTauChuyen, javax.swing.GroupLayout.Alignment.LEADING))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jpIfveLayout.setVerticalGroup(jpIfveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jpIfveLayout.createSequentialGroup().addGap(12, 12, 12).addComponent(lbTauChuyen)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(lbThoiGianLen)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lbToaCho)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lbMoTaVe)
						.addContainerGap(31, Short.MAX_VALUE)));

		groupDiVe.add(jrDi);
		jrDi.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		jrDi.setText("Chiều đi");
		jrDi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jrDiMouseClicked(evt);
			}
		});

		groupDiVe.add(jrVe);
		jrVe.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
		jrVe.setText("Chiều về");
		jrVe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jrVeActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jpChieuLayout = new javax.swing.GroupLayout(jpChieu);
		jpChieuLayout.setHorizontalGroup(
			jpChieuLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jpChieuLayout.createSequentialGroup()
					.addGap(256)
					.addComponent(jrDi)
					.addGap(142)
					.addComponent(jrVe)
					.addContainerGap(263, Short.MAX_VALUE))
		);
		jpChieuLayout.setVerticalGroup(
			jpChieuLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jpChieuLayout.createSequentialGroup()
					.addGroup(jpChieuLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jrDi, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(jrVe, GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE))
					.addContainerGap())
		);
		jpChieu.setLayout(jpChieuLayout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(listIconTau, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scp, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(jpIfHanhKhach, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
								.addComponent(jpIfve, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jpIfHanhTrinh, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jpChucNang, GroupLayout.PREFERRED_SIZE, 783, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(jpChieu, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
									.addComponent(formIfToa1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 787, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(formTabelVe, GroupLayout.PREFERRED_SIZE, 480, Short.MAX_VALUE)))
					.addContainerGap(405, GroupLayout.PREFERRED_SIZE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jpIfve, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
							.addGroup(layout.createSequentialGroup()
								.addComponent(scp, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
								.addGap(38)
								.addComponent(listIconTau, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jpIfHanhKhach, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
								.addComponent(jpIfHanhTrinh, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE))))
					.addGap(10)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(formIfToa1, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jpChieu, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jpChucNang, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addComponent(formTabelVe, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(642, Short.MAX_VALUE))
		);
		this.setLayout(layout);
	}// </editor-fold>//GEN-END:initComponents

	private void cbDTAction(ActionEvent e) {
		int index = tbListVe.getSelectedRow();
		if (index < 0)
			return;
		String doiTuong = model.getValueAt(index, 2).toString();
		if (doiTuong.equalsIgnoreCase("Trẻ em")) {

			DateTimeFormatter formatterHD = DateTimeFormatter.ofPattern("ddMMyyyy");
			JDialog dialog = new JDialog();

			JDateChooser dateChooser = new JDateChooser();
			dialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					model.setValueAt("",index,0);
					model.setValueAt(0, index, 5);
					String tien = model.getValueAt(index, 4).toString();
					model.setValueAt(tien, index, 6);
					model.setValueAt("Người lớn", index, 2);
				}
				
				
			});
			dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if ("date".equals(evt.getPropertyName())) {
						
						LocalDate dateChon = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						if(LocalDate.now().getYear() - dateChon.getYear() < 6) {
							JOptionPane.showConfirmDialog(null, "Trẻ dưới 6 tuổi được miễn phí vé");
							model.setValueAt("Trẻ em", index, 2);
							dialog.setVisible(false);
							dialog.dispose();
							return;
						}
						if(LocalDate.now().getYear() - dateChon.getYear() > 13) {
							JOptionPane.showConfirmDialog(null, "Tuổi không nằm trong trường trình áp dụng");
							model.setValueAt("Người lớn", index, 2);
							dialog.setVisible(false);
							dialog.dispose();
							return;
						}
						String key = dateChon.format(formatterHD);
						List<KhachHang> listccTam = khachHangDao.layKhachHangThuocMa(key);
						int c = 0;
						if (liscccd.containsKey(key))
							c = liscccd.get(key).size();

						int size = 0;
						if (listccTam.size() > 0) {
							String cccdCuoi = listccTam.get(0).getCccd();
							size = Integer.parseInt(cccdCuoi.substring(cccdCuoi.length() - 4, cccdCuoi.length()));
						}
						String cctam = ((listccTam.size() + c) / 1000 > 0) ? "" + (size + c + 1)
								: ((listccTam.size() + c) / 100 > 0) ? "0" + (size + 1 + c)
										: ((listccTam.size() + c) / 10 > 0) ? "00" + (size + 1 + c)
												: "000" + (size + 1 + c);
						model.setValueAt(key + cctam, index, 0);
						if (liscccd.containsKey(key))
							liscccd.get(key).add(key + cctam);
						else {
							Set<String> set = new HashSet<String>();
							set.add(key + cctam);
							liscccd.put(key, set);
						}
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
			});
			dialog.getContentPane().add(dateChooser);
			dialog.setLocationRelativeTo(cbDT);
			dialog.setModal(true);
			dialog.pack();
			dialog.setVisible(true);
//    		List<KhachHang> khachHangs = khachHangDao.layKhachHangThuocMa(doiTuong);

		}
		KhuyenMai km = kmDao.layKhuyenMaiTotNhatBangLoai(doiTuong);
		if (km != null) {
			Model_InfoVe veif = listInfoVes.get(index);
			model.setValueAt(
					(int) (veif.getChoNgoi().getGia() * veif.getChuyen().getKhoangCachGa()) * km.getChietKhau(), index,
					5);
			model.setValueAt(
					(int) (veif.getChoNgoi().getGia() * veif.getChuyen().getKhoangCachGa()) * (1 - km.getChietKhau()),
					index, 6);
		} else {
			Model_InfoVe veif = listInfoVes.get(index);
			model.setValueAt(0, index, 5);
			model.setValueAt(veif.getChoNgoi().getGia() * veif.getChuyen().getKhoangCachGa() , index, 6);
		}
	}

	private void rdMotChieuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rdMotChieuActionPerformed
		dateVe.setEnabled(false);
	}// GEN-LAST:event_rdMotChieuActionPerformed

	private void btnTimChuyenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnTimChuyenActionPerformed
		String gaDi = jtGaDi.getText();
		String gaDen = jtGaDen.getText();
		Ga ga1 = gaDao.getGaByTen(gaDi);
		Ga ga2 = gaDao.getGaByTen(gaDen);
		if (ga1 == null) {
			JOptionPane.showMessageDialog(null, "Ga không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (ga2 == null) {
			JOptionPane.showMessageDialog(null, "Ga không tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (dateDi.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Chưa chọn ngày đi", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (dateVe.getDate() == null && rdHoiKhu.isSelected()) {
			JOptionPane.showMessageDialog(null, "Chưa chọn ngày về", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		List<String> listTuyens = new TuyenDao(emf).layTuyenChuaGa(ga1.getId(), ga2.getId());
		if (listTuyens.size() == 0) {
			JOptionPane.showMessageDialog(null, "Không có tàu đi tuyến của bạn", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		LocalDate ngDi = dateDi.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ngVe = null;
		if (rdHoiKhu.isSelected()) {
			ngVe = dateVe.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (!ngVe.isAfter(ngDi)) {
				JOptionPane.showMessageDialog(null, "Ngày về phải sau ngày đi", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		List<Chuyen> listChuyenDis = new ArrayList<Chuyen>();
		for (String maTuyen : listTuyens) {
			List<Chuyen> listtam = new ChuyenDao(emf).getAllChuyenByNgay(ngDi, ga1.getId() < ga2.getId(), maTuyen);
			listChuyenDis.addAll(listtam);
		}
		if (listChuyenDis.size() == 0) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy chuyến phù hợp!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		List<Chuyen> listChuyenVes = new ArrayList<Chuyen>();
		if (ngVe != null) {
			for (String maTuyen : listTuyens) {
				List<Chuyen> listtam = new ChuyenDao(emf).getAllChuyenByNgay(ngVe, ga1.getId() > ga2.getId(), maTuyen);
				listChuyenVes.addAll(listtam);
			}
			if (listChuyenVes.size() == 0) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy chuyến về!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}

		listTau.removeAll();
		this.gaDi = ga1;
		this.gaDen = ga2;
		this.listChuyens = listChuyenDis;
		this.ngayDi = ngDi;
		this.ngayVe = ngVe;
		this.isMotChieu = rdMotChieu.isSelected();
		jrDi.setSelected(true);
		isJrDi = true;
		isJrVe = false;
		AddDataTau();
		updateDataChuyen();

	}// GEN-LAST:event_btnTimChuyenActionPerformed

	private void btnXuLyTreoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXuLyTreoActionPerformed
		if (formDonTreo == null || !formDonTreo.isVisible()) {
			formDonTreo = new FormListDontreo(emf);
			formDonTreo.addWindowListener(new WindowAdapter() {
				@Override
				public void windowDeactivated(WindowEvent e) {
					if (formDonTreo.isClick()) {
						hoaDon = formDonTreo.getHoaDon();
						System.out.println(hoaDon.getListVes().size());
						XoaTrang();
						addDataHoaDon();
					}

				}
			});
			formDonTreo.setVisible(true);
		}
	}// GEN-LAST:event_btnXuLyTreoActionPerformed

	private void rdHoiKhuMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_rdHoiKhuMouseClicked
		dateVe.setEnabled(true);
	}// GEN-LAST:event_rdHoiKhuMouseClicked

	private void rdMotChieuMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_rdMotChieuMouseClicked
		jrVe.setEnabled(false);
		isMotChieu = true;
		jrDi.setSelected(true);
	}// GEN-LAST:event_rdMotChieuMouseClicked

	private void jtGaDiMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jtGaDiMouseClicked
		String text = jtGaDi.getText().toLowerCase();
		search.setData(search(text));
		search.addEventClick(new EvenItemGaClick() {
			public void itemClick(DataSearch data) {
				menu.setVisible(false);
				jtGaDi.setText(data.getText());
			}
		});
		if (search.getItemSize() > 0) {
			menu.show(jtGaDi, 0, jtGaDi.getHeight());
			menu.setPopupSize(jtGaDi.getWidth(), (search.getItemSize() * 45));
		}
	}// GEN-LAST:event_jtGaDiMouseClicked

	private void jtGaDenKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jtGaDenKeyReleased
		search.addEventClick(new EvenItemGaClick() {
			public void itemClick(DataSearch data) {
				menu.setVisible(false);
				jtGaDen.setText(data.getText());
			}
		});
		String text = jtGaDen.getText().toLowerCase();
		search.setData(search(text));
		if (search.getItemSize() >= 0) {
			menu.show(jtGaDen, 0, jtGaDen.getHeight());
			menu.setPopupSize(jtGaDen.getWidth(), (search.getItemSize() * 45));
		}
	}// GEN-LAST:event_jtGaDenKeyReleased

	private void jtGaDenMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jtGaDenMouseClicked
		String text = jtGaDen.getText().toLowerCase();
		search.setData(search(text));
		search.addEventClick(new EvenItemGaClick() {
			public void itemClick(DataSearch data) {
				menu.setVisible(false);
				jtGaDen.setText(data.getText());
			}
		});
		if (search.getItemSize() > 0) {
			menu.show(jtGaDen, 0, jtGaDen.getHeight());
			menu.setPopupSize(jtGaDen.getWidth(), (search.getItemSize() * 45));
		}
	}// GEN-LAST:event_jtGaDenMouseClicked

	private void jtGaDiKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jtGaDiKeyReleased
		search.addEventClick(new EvenItemGaClick() {
			public void itemClick(DataSearch data) {
				menu.setVisible(false);
				jtGaDi.setText(data.getText());
			}
		});
		String text = jtGaDi.getText().toLowerCase();
		search.setData(search(text));
		if (search.getItemSize() >= 0) {
			menu.show(jtGaDi, 0, jtGaDi.getHeight());
			menu.setPopupSize(jtGaDi.getWidth(), (search.getItemSize() * 45));
		}
	}// GEN-LAST:event_jtGaDiKeyReleased

	private void btnXacNhanMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnXacNhanMouseClicked
		if (frameMuaVe == null || !frameMuaVe.isVisible()) {
			if (listInfoVes.size() == 0) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn chổ ngồi", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (hoaDon == null) {
				LocalDateTime currentDateTime = LocalDateTime.now();
				DateTimeFormatter formatterHD = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
				DateTimeFormatter formatterVe = DateTimeFormatter.ofPattern("HHmmddMMyyyy");
				String mave = "";
				String dem = "";
				String cccd = jtCccd.getText().trim();
				String hoTen = jtHoT.getText().trim();
				String doiTuong = "";
				String sdt = jtSdt.getText().trim();
				String email = jtEm.getText().trim();

				KhachHang kh = new KhachHang(cccd, sdt, hoTen, email);
//				if (checkData(kh)> 0) {
//					showMessageValue(checkData(kh), "Khách hàng đặt vé");
//					hoaDon = null;
//					return;
//				}
				String maNhanVien = taiKhoan.getNhanVien().getMaNhanVien();
				String maHD = "HD" + currentDateTime.format(formatterHD)
						+ maNhanVien.substring(maNhanVien.length() - 4);
				hoaDon = new HoaDon(maHD, LocalTime.now(), LocalDate.now(), true);
				hoaDon.setKhachHang(kh);
				KhuyenMai km = kmDao.layKhuyenMaiTotNhatBangLoai(listInfoVes.size());
				Set<KhuyenMai> listKM = null;
				if (km != null) {
					listKM = new HashSet<KhuyenMai>();
					listKM.add(km);
				}
				km = kmDao.layKhuyenMaiTotNhatBangLoai("Vé");
				if (km != null) {
					if (listKM != null)
						listKM.add(km);
					else {
						listKM = new HashSet<KhuyenMai>();
						listKM.add(km);
					}
				}

				hoaDon.setLisKhuyenMais(listKM);
				hoaDon.setNhanVien(taiKhoan.getNhanVien());
				List<Ve> listVeMa = veDao.layVeThuocMa(currentDateTime.format(formatterVe));
				List<Ve> listVe = new ArrayList<Ve>();
				for (int i = 0; i < model.getRowCount(); i++) {
					cccd = model.getValueAt(i, 0).toString();
					hoTen = model.getValueAt(i, 1).toString();
					doiTuong = model.getValueAt(i, 2).toString();
					kh = khachHangDao.getKhachHangByCCCD(cccd);
					if (kh == null)
						kh = new KhachHang(cccd, hoTen, doiTuong);
//					if (checkDataVe(kh) > 0) {
//						tbListVe.setRowSelectionInterval(i, i);
//						hoaDon = null;
//						showMessageValue(checkDataVe(kh), "vé");
//						return;
//					}
					dem = ((listVeMa.size() + i) / 100 > 0) ? "" + (listVeMa.size() + i)
							: ((listVeMa.size() + i) / 10 > 0) ? "0" + (listVeMa.size() + i)
									: "00" + (listVeMa.size() + i);
					mave = currentDateTime.format(formatterVe) + dem + maNhanVien.substring(maNhanVien.length() - 4);
					Ve ve = new Ve(mave, listInfoVes.get(i).getChuyen().getDateLenTau(), true);
					km = kmDao.layKhuyenMaiTotNhatBangLoai(doiTuong);

					Set<ChiTietVe> listCTV = new HashSet<ChiTietVe>();
					listCTV.add(new ChiTietVe(ve, listInfoVes.get(i).getChuyen().getGaDi(), true));
					listCTV.add(new ChiTietVe(ve, listInfoVes.get(i).getChuyen().getGaDen(), false));
					ve.setLisChiTietVes(listCTV);
					ve.setKhuyenMai(km);
					ve.setHoaDon(hoaDon);
					ve.setKhachHang(kh);
					ve.setChuyen(listInfoVes.get(i).getChuyen().getChuyen());
					ve.setChoNgoi(listInfoVes.get(i).getChoNgoi());
					listVe.add(ve);
					dem = "";
					mave = "";
				}
				hoaDon.setListVes(listVe);
			}
			if(hoaDon != null && !hoaDon.isTrangThai()) {
				LocalDateTime currentDateTime = LocalDateTime.now();
				DateTimeFormatter formatterVe = DateTimeFormatter.ofPattern("HHmmddMMyyyy");
				String maNhanVien = taiKhoan.getNhanVien().getMaNhanVien();
				String cccd = "";
				String hoTen = "";
				String doiTuong = "";
				KhachHang kh = null;
				String mave = "";
				String dem = "";
				List<Ve> listVeMa = veDao.layVeThuocMa(currentDateTime.format(formatterVe));
				for (int i = hoaDon.getListVes().size(); i < model.getRowCount(); i++) {
					cccd = model.getValueAt(i, 0).toString();
					hoTen = model.getValueAt(i, 1).toString();
					doiTuong = model.getValueAt(i, 2).toString();
					kh = khachHangDao.getKhachHangByCCCD(cccd);
					if(cccd.equalsIgnoreCase(hoaDon.getKhachHang().getCccd())) {
						hoaDon.getKhachHang().setDoiTuong(doiTuong);
					}
					KhuyenMai km = null;
					if (kh == null) {
						kh = new KhachHang(cccd, hoTen, doiTuong);
					}
					if (checkDataVe(kh) > 0) {
						tbListVe.setRowSelectionInterval(i, i);
						showMessageValue(checkData(kh), "vé");
						return;
					}
					dem = ((listVeMa.size() + i) / 100 > 0) ? "" + (listVeMa.size() + i)
							: ((listVeMa.size() + i) / 10 > 0) ? "0" + (listVeMa.size() + i)
									: "00" + (listVeMa.size() + i);
					mave = currentDateTime.format(formatterVe) + dem + maNhanVien.substring(maNhanVien.length() - 4);
					Ve ve = new Ve(mave, listInfoVes.get(i).getChuyen().getDateLenTau(), true);
					km = kmDao.layKhuyenMaiTotNhatBangLoai(doiTuong);

					Set<ChiTietVe> listCTV = new HashSet<ChiTietVe>();
					listCTV.add(new ChiTietVe(ve, listInfoVes.get(i).getChuyen().getGaDi(), true));
					listCTV.add(new ChiTietVe(ve, listInfoVes.get(i).getChuyen().getGaDen(), false));
					ve.setLisChiTietVes(listCTV);
					ve.setKhuyenMai(km);
					ve.setHoaDon(hoaDon);
					ve.setKhachHang(kh);
					ve.setChuyen(listInfoVes.get(i).getChuyen().getChuyen());
					ve.setChoNgoi(listInfoVes.get(i).getChoNgoi());
					hoaDon.getListVes().add(ve);
					dem = "";
					mave = "";
				}
			}
			if (frameMuaVe == null) {
				frameMuaVe = new jFrameMuaVe(emf, hoaDon);
				frameMuaVe.addWindowListener(new WindowAdapter() {
					@Override
					public void windowDeactivated(WindowEvent e) {
						if (frameMuaVe.isAddHoaDon()) {
							XoaTrang();
							hoaDon = null;
							AddDataTau();

						}

					}
				});
				frameMuaVe.setVisible(true);
			} else {
				frameMuaVe.setHoadon(hoaDon);
				frameMuaVe.setVisible(true);
			}

		}

	}// GEN-LAST:event_btnXacNhanMouseClicked

	private void showMessageValue(int check, String loai) {
		switch (check) {
		case 1:
			JOptionPane.showMessageDialog(null, "CCCD là 12 ký số", "Lỗi Thông tin " + loai,
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Họ tên không chứa số và ký tự đặc biệt", "Lỗi Thông tin " + loai,
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "Email không đúng(example@gmail.com)", "Lỗi Thông tin " + loai,
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 3:

			JOptionPane.showMessageDialog(null, "Số điện thoại không chính xác", "Lỗi Thông tin " + loai,
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}

	private void addDataHoaDon() {
		jtCccd.setText(hoaDon.getKhachHang().getCccd());
		jtHoT.setText(hoaDon.getKhachHang().getHoTen());
		jtSdt.setText(hoaDon.getKhachHang().getSdt());
		jtEm.setText(hoaDon.getKhachHang().getEmail());
		for (Ve ve : hoaDon.getListVes()) {
			String maChuyen = ve.getChuyen().getMaChuyen();
			if (!listChoChon.containsKey(maChuyen)) {
				Set<ChoNgoi> listCN = new HashSet<ChoNgoi>();
				listCN.add(ve.getChoNgoi());
				listChoChon.put(maChuyen, listCN);
			} else {
				listChoChon.get(maChuyen).add(ve.getChoNgoi());
			}
		}
		for (ChiTietVe ctv : hoaDon.getListVes().get(0).getLisChiTietVes()) {
			if (ctv.isChieu())
				gaDi = ctv.getGa();
			else
				gaDen = ctv.getGa();
		}
		isMotChieu = true;
		List<String> listTuyens = new TuyenDao(emf).layTuyenChuaGa(gaDi.getId(), gaDen.getId());
		List<Chuyen> listChuyenVes = new ArrayList<Chuyen>();
		ngayDi = hoaDon.getListVes().get(0).getChuyen().getNgayKhoiHanh();
		for (String maTuyen : listTuyens) {
			List<Chuyen> listtam = new ChuyenDao(emf).getAllChuyenByNgay(ngayDi, gaDi.getId() < gaDen.getId(), maTuyen);
			listChuyenVes.addAll(listtam);
		}
		this.listChuyens = listChuyenVes;
		createModel_IfVes();
		updateDataChuyen();
		AddDataTau();
	}

	private void createModel_IfVes() {
		if (hoaDon == null)
			return;
		for (Ve ve : hoaDon.getListVes()) {
			Model_Tau modelTau = null;
			Ga gd = null;
			Ga gde = null;
			for (ChiTietVe ctv : ve.getLisChiTietVes()) {
				if (ctv.isChieu())
					gd = ctv.getGa();
				else
					gde = ctv.getGa();
			}
			if (gd.getId() < gde.getId())
				modelTau = new Model_Tau(ve.getChuyen(), gd, gde, gaDau);
			else
				modelTau = new Model_Tau(ve.getChuyen(), gd, gde, gaCuoi);
			Model_InfoVe veIf = new Model_InfoVe(modelTau, ve.getChoNgoi(), null);
			KhuyenMai km = ve.getKhuyenMai();
			int gia = (int) Math.round(ve.getChoNgoi().getGia() * Math.abs(gd.getId() - gde.getId()));
			model.addRow(new Object[] { ve.getKhachHang().getCccd(), ve.getKhachHang().getHoTen(),
					ve.getKhachHang().getDoiTuong(), veIf.toString(), gia,
					km == null ? 0 : gia * ve.getKhuyenMai().getChietKhau(),
					km == null ? gia : gia * (1 - km.getChietKhau()) });
			listInfoVes.add(veIf);
		}

	}

	private void XoaTrang() {
		listInfoVes.clear();
		listChoChon.clear();
		liscccd.clear();
		DefaultTableModel md = (DefaultTableModel) tbListVe.getModel();
		md.setRowCount(0);
		listTau.removeAll();
		jtCccd.setText("");
		jtHoT.setText("");
		jtSdt.setText("");
		jtEm.setText("");
	}

	private int checkDataVe(KhachHang khachHang) {
		if (!khachHang.getCccd().trim().matches("\\d{12}")) {
			return 1;
		}
		if (!khachHang.getHoTen().trim().matches("^[^!@#$%^&*()\\d]+$")) {
			return 2;
		}
		return 0;
	}

	private int checkData(KhachHang khachHang) {
		if (!khachHang.getCccd().trim().matches("\\d{12}")) {
			return 1;
		}
		if (!khachHang.getHoTen().trim().matches("^[^!@#$%^&*()\\d]+$")) {
			return 2;
		}
		if (!khachHang.getSdt().trim().matches("^0\\d{9,10}$")) {
			return 3;
		}
		if (!khachHang.getEmail().trim().matches("^[a-zA-Z0-9._%+-]+@(gmail|email)\\.com$")) {
			return 4;
		}
		return 0;
	}

	private void tbListVeMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tbListVeMouseClicked
		int index = tbListVe.getSelectedRow();
		if (index == -1)
			return;
		Model_InfoVe info = listInfoVes.get(index);
		lbTauChuyen.setText(info.getThongTinChuyen());
		lbThoiGianLen.setText(info.getChuyen().getTGDi());
		lbToaCho.setText(info.getInfoCho());
		lbMoTaVe.setText(info.getcheckCho(info.getChoNgoi().getMoTa(), info.getChoNgoi().getViTri()));
	}// GEN-LAST:event_tbListVeMouseClicked

	private void btnHuyChoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnHuyChoMouseClicked
		int index = tbListVe.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(null, "chưa chọn vé cần hủy!");
			return;
		}

		Model_InfoVe infoVe = listInfoVes.get(index);
		if (infoVe.getItem() == null) {
			JOptionPane.showMessageDialog(null, "Vé đã thanh toán không được hủy!");
			return;
		}
		((ChoNgoiItem) infoVe.getItem()).setSelected(false);
		String maC = infoVe.getChuyen().getChuyen().getMaChuyen();
		if (listChoChon.containsKey(maC)) {
			listChoChon.get(maC).remove(infoVe.getChoNgoi());
		}
		listInfoVes.remove(index);
		model.removeRow(index);
		lbTauChuyen.setText(" ");
		lbThoiGianLen.setText(" ");
		lbToaCho.setText(" ");
		lbMoTaVe.setText(" ");
	}// GEN-LAST:event_btnHuyChoMouseClicked

	private void jrDiMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jrDiMouseClicked
		if (isJrDi)
			return;
		isJrVe = !isJrVe;
		isJrDi = !isJrDi;
		List<String> listTuyens = new TuyenDao(emf).layTuyenChuaGa(gaDi.getId(), gaDen.getId());
		List<Chuyen> listChuyenVes = new ArrayList<Chuyen>();
		for (String maTuyen : listTuyens) {
			List<Chuyen> listtam = new ChuyenDao(emf).getAllChuyenByNgay(ngayDi, gaDi.getId() > gaDen.getId(), maTuyen);
			listChuyenVes.addAll(listtam);
		}
		listTau.removeAll();
		Ga gatam1 = gaDi;
		Ga gatam2 = gaDen;
		this.gaDi = gatam2;
		this.gaDen = gatam1;
		this.listChuyens = listChuyenVes;
		AddDataTau();
		updateDataChuyen();
	}// GEN-LAST:event_jrDiMouseClicked

	private void jrVeActionPerformed(java.awt.event.ActionEvent evt) {
		if (isJrVe)
			return;
		isJrVe = !isJrVe;
		isJrDi = !isJrDi;
		List<String> listTuyens = new TuyenDao(emf).layTuyenChuaGa(gaDi.getId(), gaDen.getId());
		List<Chuyen> listChuyenVes = new ArrayList<Chuyen>();
		for (String maTuyen : listTuyens) {
			List<Chuyen> listtam = new ChuyenDao(emf).getAllChuyenByNgay(ngayVe, gaDi.getId() > gaDen.getId(), maTuyen);
			listChuyenVes.addAll(listtam);
		}
		listTau.removeAll();
		Ga gatam1 = gaDi;
		Ga gatam2 = gaDen;
		this.gaDi = gatam2;
		this.gaDen = gatam1;
		this.listChuyens = listChuyenVes;
		AddDataTau();
		updateDataChuyen();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.ButtonGroup btnGroup;
	private javax.swing.JButton btnHuyCho;
	private javax.swing.JButton btnTimChuyen;
	private javax.swing.JButton btnXacNhan;
	private javax.swing.JButton btnXuLyTreo;
	private com.toedter.calendar.JDateChooser dateDi;
	private com.toedter.calendar.JDateChooser dateVe;
	private component.FormIfToa formIfToa1;
	private component.FormTabelVe formTabelVe;
	private javax.swing.ButtonGroup groupDiVe;
	private javax.swing.JPanel jpChieu;
	private javax.swing.JPanel jpChucNang;
	private javax.swing.JPanel jpIfHanhKhach;
	private javax.swing.JPanel jpIfHanhTrinh;
	private javax.swing.JPanel jpIfToa;
	private javax.swing.JPanel jpIfve;
	private javax.swing.JRadioButton jrDi;
	private javax.swing.JRadioButton jrVe;
	private javax.swing.JTextField jtCccd;
	private javax.swing.JTextField jtEm;
	private javax.swing.JTextField jtGaDen;
	private javax.swing.JTextField jtGaDi;
	private javax.swing.JTextField jtHoT;
	private javax.swing.JTextField jtSdt;
	private javax.swing.JLabel lbGaDen;
	private javax.swing.JLabel lbGaDi;
	private javax.swing.JLabel lbGaDi1;
	private javax.swing.JLabel lbGaDi2;
	private javax.swing.JLabel lbGaDi3;
	private javax.swing.JLabel lbGaDi4;
	private javax.swing.JLabel lbMoTaVe;
	private javax.swing.JLabel lbNext;
	private javax.swing.JLabel lbNgayDi;
	private javax.swing.JLabel lbTauChuyen;
	private javax.swing.JLabel lbThoiGianLen;
	private javax.swing.JLabel lbToaCho;
	private javax.swing.JLabel lbVe;
	private javax.swing.JLabel lbifToa;
	private component.ListIconTau listIconTau;
	private component.ListTau listTau;
	private javax.swing.JRadioButton rdHoiKhu;
	private javax.swing.JRadioButton rdMotChieu;
	private javax.swing.JScrollPane scp;
	private javax.swing.JScrollPane scpTbVe;
	private javax.swing.JScrollPane spListKhoang;
	private javax.swing.JTable tbListVe;
	// End of variables declaration//GEN-END:variables
}
