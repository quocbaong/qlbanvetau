package dao;

import java.util.List;

import entity.Tuyen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class TuyenDao {
	 private EntityManager em;

	    public TuyenDao(EntityManagerFactory emf) {
	        em = emf.createEntityManager();
	    }
//	    lấy Tuyen bằng mã
	    public Tuyen layTuyenBangMa(String ma) {
	    	return em.find(Tuyen.class, ma);
	    }
//	    lấy toàn bộ danh sách Tuyến
	    public List<Tuyen> getAllTuyen(){
	    	return em.createNamedQuery("Tuyen.findAll", Tuyen.class).getResultList();
	    }
	    
	    public List<String> layTuyenChuaGa(int id1, int id2){
	    	return em.createQuery("SELECT t.maTuyen FROM Tuyen t JOIN t.listGas ctt WHERE ctt.id = :id1 OR ctt.id = :id2 GROUP BY t.maTuyen", String.class)
	    			.setParameter("id1", id1)
	    			.setParameter("id2", id2).getResultList();
	    }
}
