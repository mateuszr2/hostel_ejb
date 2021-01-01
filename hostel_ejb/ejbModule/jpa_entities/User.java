package jpa_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;

	private int active;

	private String country;

	private String email;



	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_login")
	private Date lastLogin;

	private String login;

	private BigInteger mobile;

	private String name;

	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="register_date")
	private Date registerDate;

	//bi-directional many-to-one association to ActionLog
	@OneToMany(mappedBy="user")
	private List<ActionLog> actionLogs;

	//bi-directional many-to-one association to RoomBooking
	@OneToMany(mappedBy="user")
	private List<RoomBooking> roomBookings;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="id_role")
	private Role role;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public BigInteger getMobile() {
		return this.mobile;
	}

	public void setMobile(BigInteger mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public List<ActionLog> getActionLogs() {
		return this.actionLogs;
	}

	public void setActionLogs(List<ActionLog> actionLogs) {
		this.actionLogs = actionLogs;
	}

	public ActionLog addActionLog(ActionLog actionLog) {
		getActionLogs().add(actionLog);
		actionLog.setUser(this);

		return actionLog;
	}

	public ActionLog removeActionLog(ActionLog actionLog) {
		getActionLogs().remove(actionLog);
		actionLog.setUser(null);

		return actionLog;
	}

	public List<RoomBooking> getRoomBookings() {
		return this.roomBookings;
	}

	public void setRoomBookings(List<RoomBooking> roomBookings) {
		this.roomBookings = roomBookings;
	}

	public RoomBooking addRoomBooking(RoomBooking roomBooking) {
		getRoomBookings().add(roomBooking);
		roomBooking.setUser(this);

		return roomBooking;
	}

	public RoomBooking removeRoomBooking(RoomBooking roomBooking) {
		getRoomBookings().remove(roomBooking);
		roomBooking.setUser(null);

		return roomBooking;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}