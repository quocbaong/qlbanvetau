package dao;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import entity.KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class KhuyenMaiDao {
	private EntityManager em;

	public KhuyenMaiDao(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	public boolean updateKhuyenMai(KhuyenMai khuyenMai) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(khuyenMai);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}

	public boolean addKhuyenMai(KhuyenMai khuyenMai) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(khuyenMai);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}

	// lấy Khuyến mãi bằng mã
	public KhuyenMai getKhuyenMaiByMa(String ma) {
		return em.find(KhuyenMai.class, ma);
	}

	// lấy toàn bộ danh sách khuyến mãi
	public List<KhuyenMai> getAllKhuyenMai() {
		return em.createNamedQuery("KhuyenMai.findAll", KhuyenMai.class).getResultList();
	}

	// lấy toàn bộ danh sách khuyến mãi khach hang
	public List<String> getAllKhuyenMaiKHLoai() {
		return em.createQuery(
				"select km.loaiKhuyenMai from KhuyenMai km where km.loaiKhuyenMai != :loai GROUP BY km.loaiKhuyenMai",
				String.class).setParameter("loai", "KMHD").getResultList();
	}

	public List<KhuyenMai> getAllKhuyenMaiKH() {
		return em.createNamedQuery("KhuyenMai.findAllKMKH", KhuyenMai.class).setParameter("loai", "KMHD")
				.getResultList();
	}

	// lấy toàn bộ danh sách khuyến mãi tren hoa don
	public List<KhuyenMai> getAllKhuyenMaiHD() {
		return em.createNamedQuery("KhuyenMai.findAllKMHD", KhuyenMai.class).setParameter("loai", "KMHD")
				.getResultList();
	}

	public List<KhuyenMai> getAllKhuyenMaiByNumber(String ma) {
		return em.createNamedQuery("KhuyenMai.findAllKMNB", KhuyenMai.class).setParameter("ma", "%" + ma + "%")
				.getResultList();
	}

	public List<KhuyenMai> getKhuyenMaiByLoaiKhuyenMaiKH(String loai) {
		return em.createQuery("SELECT km FROM KhuyenMai km WHERE km.loaiKhuyenMai = :loai", KhuyenMai.class)
				.setParameter("loai", loai).getResultList();

	}
        // Thêm phương thức để lấy danh sách khuyến mãi trong một khoảng thời gian
    public List<KhuyenMai> getKhuyenMaiByTimeRange(Date startTime, Date endTime) {
        return em.createQuery("SELECT km FROM KhuyenMai km WHERE km.thoiGianBatDau >= :startTime AND km.thoiGianKetThuc <= :endTime", KhuyenMai.class)
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
                .getResultList();
    }
    
//    lấy khuyến mãi theo loại trong 1 khoảng thười gian
    public List<KhuyenMai> layDSKhuyenMaiTheoKhoangThoiGian(Date startTime, Date endTime,String loai) {
        return em.createQuery("SELECT km FROM KhuyenMai km WHERE km.thoiGianBatDau >= :startTime AND km.thoiGianKetThuc <= :endTime AND km.loaiKhuyenMai = :loai", KhuyenMai.class)
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
                .setParameter("loai", loai)
                .getResultList();
    }
	
	public KhuyenMai layKhuyenMaiTotNhatBangLoai(int soLuong) {
		try {
			return em.createQuery(
					"SELECT km FROM KhuyenMai km WHERE AND km.soLuongVe <= :soLuong km.loaiKhuyenMai = :loai AND km.trangThai = :trangThai AND km.thoiGianKetThuc > :date ORDER BY km.soLuongVe DESC",
					KhuyenMai.class).setParameter("soLuong", soLuong).setParameter("loai", "KMKH").setParameter("trangThai", true).setParameter("date", Date.from(Instant.now())).setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public KhuyenMai layKhuyenMaiTotNhatBangLoai(String loai) {
		try {
			return em.createQuery(
					"SELECT km FROM KhuyenMai km WHERE km.loaiKhuyenMai = :loai AND km.trangThai = :trangThai AND km.thoiGianKetThuc > :date ORDER BY km.chietKhau DESC",
					KhuyenMai.class).setParameter("loai", loai).setParameter("trangThai", true).setParameter("date", Date.from(Instant.now())).setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
