{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = with pkgs;[
    xorg.libXtst
    libGL
    gtk3
    glib
  ];

  # Setting up the LD_LIBRARY_PATH for your JavaFX project
  shellHook = ''
    export LD_LIBRARY_PATH="${pkgs.libGL}/lib:${pkgs.gtk3}/lib:${pkgs.glib.out}/lib:${pkgs.xorg.libXtst}/lib"
  '';
}
