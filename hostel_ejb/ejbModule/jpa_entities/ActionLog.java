package jpa_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the action_log database table.
 * 
 */
@Entity
@Table(name="action_log")
@NamedQuery(name="ActionLog.findAll", query="SELECT a FROM ActionLog a")
public class ActionLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_log")
	private int idLog;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	private String log;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public ActionLog() {
	}
	public ActionLog(Date data, String log) {
		this.setDatetime(data);
		this.setLog(log);
		
	}

	public int getIdLog() {
		return this.idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getLog() {
		return this.log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}