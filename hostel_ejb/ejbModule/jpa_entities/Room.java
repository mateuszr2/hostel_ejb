package jpa_entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rooms database table.
 * 
 */
@Entity
@Table(name="rooms")
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_room")
	private int idRoom;

	private String image;
	
	private String description;

	private byte occupied;

	private double price;

	@Column(name="room_no")
	private int roomNo;

	private String type;

	//bi-directional one-to-one association to RoomBooking
	@OneToOne(mappedBy="room")
	private RoomBooking roomBooking;

	public Room() {
	}

	public Integer getIdRoom() {
		return this.idRoom;
	}

	public void setIdRoom(Integer idRoom) {
		this.idRoom = idRoom;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getOccupied() {
		return this.occupied;
	}

	public void setOccupied(byte occupied) {
		this.occupied = occupied;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RoomBooking getRoomBooking() {
		return this.roomBooking;
	}

	public void setRoomBooking(RoomBooking roomBooking) {
		this.roomBooking = roomBooking;
	}

}