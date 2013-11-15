package ee.ut.math.tvt.salessystem.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * System client. Every client has a name and a discount percentage. This object
 * is used by hibernate and corresponds to a table in database.
 */
@Entity
@Table(name = "CLIENT")
public class Client implements DisplayableItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String Name;

    @Column(name = "discount")
    private Integer discountPercentage;

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Name;
    }

	@Override
<<<<<<< HEAD
=======
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
>>>>>>> 15666a27326ba8a19b8104f53a3afaa04f5f3511
	public int getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}
}
