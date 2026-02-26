package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OtpCode {
    private UUID id;
    private String telephone;
    private String code;
    private LocalDateTime expires_at;
    private boolean is_used;
    private LocalDateTime created_at;

    // Getters et setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public LocalDateTime getExpires_at() { return expires_at; }
    public void setExpires_at(LocalDateTime expires_at) { this.expires_at = expires_at; }

    public boolean isIs_used() { return is_used; }
    public void setIs_used(boolean is_used) { this.is_used = is_used; }

    public LocalDateTime getCreated_at() { return created_at; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }

    BDQUEueLess db = new BDQUEueLess();

    // Ajouter un code OTP
    public void AddOtpCode(OtpCode otp) {
        String sql = "INSERT INTO otp_codes(telephone, code, expires_at, is_used) VALUES (?,?,?,?)";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        int i = 0;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setString(1, otp.getTelephone());
            pst.setString(2, otp.getCode());
            pst.setObject(3, otp.getExpires_at());
            pst.setBoolean(4, otp.isIs_used());

            i = pst.executeUpdate();
            if (i != 0)
                System.out.println("Code OTP ajouté avec succès !!");
            else
                System.out.println("Code OTP non ajouté !!");
            cnx.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Problème d'insertion du code OTP");
            e.printStackTrace();
        }
    }

    // Récupérer tous les codes OTP
    public List<OtpCode> getOtpCodes() {
        List<OtpCode> liste = new ArrayList<>();
        String sql = "SELECT * FROM otp_codes";
        Connection cnx = null;
        cnx = db.seconnecter();
        Statement st;
        ResultSet rs;

        try {
            st = cnx.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                OtpCode o = new OtpCode();
                o.setId((UUID) rs.getObject("id"));
                o.setTelephone(rs.getString("telephone"));
                o.setCode(rs.getString("code"));
                o.setExpires_at(rs.getObject("expires_at", LocalDateTime.class));
                o.setIs_used(rs.getBoolean("is_used"));
                o.setCreated_at(rs.getObject("created_at", LocalDateTime.class));
                liste.add(o);
            }
            System.out.println("Liste des codes OTP récupérée !!");
            cnx.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Problème lors de la récupération des codes OTP !!");
            e.printStackTrace();
        }
        return liste;
    }

    // Mettre à jour un code OTP
    public void UpdateOtpCode(OtpCode otp, UUID id_otp) {
        String sql = "UPDATE otp_codes SET telephone=?, code=?, expires_at=?, is_used=? WHERE id=?";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        int i = 0;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setString(1, otp.getTelephone());
            pst.setString(2, otp.getCode());
            pst.setObject(3, otp.getExpires_at());
            pst.setBoolean(4, otp.isIs_used());
            pst.setObject(5, id_otp);

            i = pst.executeUpdate();
            if (i != 0)
                System.out.println("Code OTP modifié avec succès !!");
            else
                System.out.println("Code OTP non modifié !!");
            cnx.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Problème lors de la modification du code OTP");
            e.printStackTrace();
        }
    }

    // Supprimer un code OTP
    public void DeleteOtpCode(UUID id_otp) {
        String sql = "DELETE FROM otp_codes WHERE id=?";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        int i = 0;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setObject(1, id_otp);

            i = pst.executeUpdate();
            if (i != 0)
                System.out.println("Code OTP supprimé avec succès !!");
            else
                System.out.println("Code OTP non supprimé !!");
            cnx.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Problème lors de la suppression du code OTP");
            e.printStackTrace();
        }
    }

    // Récupérer un code OTP par son ID
    public OtpCode getOtpCodeById(UUID id_otp) {
        OtpCode o = new OtpCode();
        String sql = "SELECT * FROM otp_codes WHERE id=?";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setObject(1, id_otp);
            rs = pst.executeQuery();
            if (rs.next()) {
                o.setId((UUID) rs.getObject("id"));
                o.setTelephone(rs.getString("telephone"));
                o.setCode(rs.getString("code"));
                o.setExpires_at(rs.getObject("expires_at", LocalDateTime.class));
                o.setIs_used(rs.getBoolean("is_used"));
                o.setCreated_at(rs.getObject("created_at", LocalDateTime.class));
            }
            rs.close();
            pst.close();
            cnx.close();
            System.out.println("Récupération par ID effectuée !!");
        } catch (SQLException e) {
            System.out.println("Problème lors de la récupération par ID !!");
            e.printStackTrace();
        }
        return o;
    }
}