package com.web.store.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 本類別封裝單筆書籍資料
@Entity
@Table(name="Book")
@XmlRootElement
public class BookBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer bookId ;
	
	private String  title;
	private String  author;
	private Double  price;
	private Double  discount;
	private String  fileName;
	private String  bookNo;
	@JsonIgnore
	private Blob    coverImage;
	private String  discountStr;
	private Integer  stock;
	private String   category;
	private CompanyBean companyBean;
	private Integer  companyId;	
	private MultipartFile  productImage;
	@Transient
	@XmlTransient
	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="companyId")
    public CompanyBean getCompanyBean() {
		return companyBean;
	}

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	
	public BookBean(Integer bookID, String title, String author, 
			Double price, Double discount, String fileName, 
			String bookNo, Blob coverImage, Integer stock, String category, CompanyBean companyBean) {
		this.bookId = bookID;
		this.title = title;
		this.author = author;
		this.price = price;
		this.discount = discount;
		this.fileName = fileName;
		this.bookNo = bookNo;
		this.coverImage = coverImage;
		this.companyBean = companyBean;
		this.category = category;
		this.stock = stock;
	}
	
	public BookBean() {
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getBookId() {   // bookId
		return bookId;
	}
	public void setBookId(Integer bookID) {
		this.bookId = bookID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	private String  priceStr = null;
	
	@Transient
	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}
	@Column(columnDefinition="decimal(10, 1)")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
		if (priceStr == null) {
			priceStr = String.valueOf(price);
		}
	}
	@Column(columnDefinition="decimal(7, 3)")
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {   //0.8, 0.75
		if (discount  == null) {
			this.discount = 1.0;
			discountStr = "";
			return;
		}
		this.discount = discount;
		if (discount == 1) {
			discountStr = "";
		} else {
			int dnt = (int)(discount * 100);
			if (dnt % 10 == 0) {
				discountStr = (dnt / 10) + "折";
			} else {
				discountStr = " "  + dnt + "折";
			} 
			
		}
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	@Transient
	public String getDiscountStr() {
		return discountStr;
	}	
	@XmlTransient
	public Blob getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(Blob coverImage) {
		this.coverImage = coverImage;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "BookBean [bookId=" + bookId + ", title=" + title + ", author=" + author + ", price=" + price
				+ ", discount=" + discount + ", fileName=" + fileName + ", bookNo=" + bookNo + ", coverImage="
				+ coverImage + ", discountStr=" + discountStr + ", stock=" + stock + ", companyBean=" + companyBean
				+ ", priceStr=" + priceStr + "]";
	}
	@Transient
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((bookNo == null) ? 0 : bookNo.hashCode());
		result = prime * result + ((companyBean == null) ? 0 : companyBean.hashCode());
		result = prime * result + ((coverImage == null) ? 0 : coverImage.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((discountStr == null) ? 0 : discountStr.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((priceStr == null) ? 0 : priceStr.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookBean other = (BookBean) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (bookNo == null) {
			if (other.bookNo != null)
				return false;
		} else if (!bookNo.equals(other.bookNo))
			return false;
		if (companyBean == null) {
			if (other.companyBean != null)
				return false;
		} else if (!companyBean.equals(other.companyBean))
			return false;
		if (coverImage == null) {
			if (other.coverImage != null)
				return false;
		} else if (!coverImage.equals(other.coverImage))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (discountStr == null) {
			if (other.discountStr != null)
				return false;
		} else if (!discountStr.equals(other.discountStr))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priceStr == null) {
			if (other.priceStr != null)
				return false;
		} else if (!priceStr.equals(other.priceStr))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
