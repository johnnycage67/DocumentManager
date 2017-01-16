package ru.ordm.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ru.ordm.utils.JsonDateSerializer;

@Entity
@Table(name = "v$pl_por_oki_oplat")
public class PlatPor {

    @Id
    // @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private String ndoc;
    
    private LocalDate dat;
    
    private float s;

    private String innpl;

    private String innpol;

    private String nampl;

    private String nampol;
    
    private String bpl_txt;
    
    private String bpol_txt;
    
    private String bpl;
    
    private String bpol;
    
    private String kschpl;
    
    private String kschpol;
    
    private String rschpl;
    
    private String rschpol;
    
    private String osn;
    
    private String vopl;
    
    private String kod;
    
    private String nazn;
    
    private String ocher;
    
    private String sprop; //Сумма прописью
    
    

    public PlatPor() {
    }

    public Long getId() {
        return id;
    }

    public String getNdoc() {
        return ndoc;
    }

    public String getInnpl() {
        return innpl;
    }

    public String getInnpol() {
        return innpol;
    }

    public String getNampl() {
        return nampl;
    }

    public String getNampol() {
        return nampol;
    }

    public void setNampl(String nampl) {
        this.nampl = nampl;
    }

    public void setNampol(String nampol) {
        this.nampol = nampol;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setNdoc(String ndoc) {
        this.ndoc = ndoc;
    }

    public void setInnpl(String innpl) {
        this.innpl = innpl;
    }

    public void setInnpol(String innpol) {
        this.innpol = innpol;
    }


	public String getBpl_txt() {
		return bpl_txt;
	}


	public void setBpl_txt(String bpl_txt) {
		this.bpl_txt = bpl_txt;
	}


	public String getBpol_txt() {
		return bpol_txt;
	}


	public void setBpol_txt(String bpol_txt) {
		this.bpol_txt = bpol_txt;
	}

	public String getBpl() {
		return bpl;
	}

	public void setBpl(String bpl) {
		this.bpl = bpl;
	}

	public String getBpol() {
		return bpol;
	}

	public void setBpol(String bpol) {
		this.bpol = bpol;
	}

	public String getKschpl() {
		return kschpl;
	}

	public void setKschpl(String kschpl) {
		this.kschpl = kschpl;
	}

	public String getKschpol() {
		return kschpol;
	}

	public void setKschpol(String kschpol) {
		this.kschpol = kschpol;
	}

	public String getRschpl() {
		return rschpl;
	}

	public void setRschpl(String rschpl) {
		this.rschpl = rschpl;
	}

	public String getRschpol() {
		return rschpol;
	}

	public void setRschpol(String rschpol) {
		this.rschpol = rschpol;
	}

	public String getOcher() {
		return ocher;
	}

	public void setOcher(String ocher) {
		this.ocher = ocher;
	}

	public String getNazn() {
		return nazn;
	}

	public void setNazn(String nazn) {
		this.nazn = nazn;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getVopl() {
		return vopl;
	}

	public void setVopl(String vopl) {
		this.vopl = vopl;
	}

	public String getOsn() {
		return osn;
	}

	public void setOsn(String osn) {
		this.osn = osn;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public LocalDate getDat() {
		return dat;
	}

	public void setDat(LocalDate dat) {
		this.dat = dat;
	}

	public float getS() {
		return s;
	}

	public void setS(float s) {
		this.s = s;
	}

	public String getSprop() {
		return sprop;
	}

	public void setSprop(String sprop) {
		this.sprop = sprop;
	}
}
