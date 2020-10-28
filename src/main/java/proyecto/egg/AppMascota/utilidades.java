
package proyecto.egg.AppMascota;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.egg.AppMascota.Entidades.Mascota;
import proyecto.egg.AppMascota.Servicios.ClienteServicio;
import proyecto.egg.AppMascota.Servicios.MascotaServicio;

public class utilidades {
    
    
    public Date convertirDate(String fecha) {
        
        try {
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
            Date convertido = fechaHora.parse(fecha);
            
            return convertido;
        } catch (ParseException ex) {
            Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(MascotaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
