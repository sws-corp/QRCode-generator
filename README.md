**QRCODE Generator by SWS**
Pour répondre au défis "Catch them all", la SWS a crée une application Java qui permet en donnant une image et un lien de créer un QR code de la forme de l'image donnée en entrée.
Par exemple si on donne en entrée une image d'un Tortank, et un lien comme google.com, le générateur créera un QR code menant vers google.com en forme de Tortank.

**Contraintes**
Les images passées en entrée doivent être au format PNG. Etre en un seul morceaux, et le fond de l'image doit être totalement transparant. 

**Utilisation**
Source: https://github.com/sws-corp/QRCode-generator
1) Télécharger le fichier SWS-QRCODE.jar
2)Lancer la commande java -jar SWS-QRCODE.jar PATH où PATH est le chemin vers le dossier de travail de l'application. Ce dossier peut ne pas exister.

**Elements d'UI**
La fenetre est composée de trois parties. Les parametres d'entrée en haut à gauche, la console à droite, et la prevue des images générées en bas à gauche.

**Parametres**
Les parametres sont: 
-le fichier de base
-le lien du QRCODE
-la version du QRCODE
Tout les parametres sont obligatoires, certain dépendent les uns des autres. Par exemple si le lien est trop long alors la version du QRCODe devra être plus élevée pour pouvoir encoder la quantité d'information. En cas de version insuffisante un message d'erreur sera afficher dans la console.

**Exemple d'utilisation**
Pour créer un QRCODE sur la base d'un magicarpe, on utilise une image de magicarpe supposée conformes aux contraintes. On rempli ensuite le lien pointé par le QRCODE. On spécifie la version du QRCODE, plus la version est élevée plus la quantité d'information permise dans le lien est grande.
Ensuite on appuye sur *Create Json*. Cela créer un fichier JSON comportant les informations nécessaire à la création d'une image. Cette opération peut etre répétée pour autant d'image que souhaité. Une fois que tout les fichiers JSON ont été créé de cette manière, il suffit d'appuyer sur *Generate Images*. Cela créera toute les images renseignées. (Attention, si une image pointe vers le même lien, alors seul la plus récente sera comptabilisée).
Les images peuvents être prévisualisée dans la fenêtre correspondante en bas à gauche. Et peuvent être accèdée via le bouton *Show Images*.
