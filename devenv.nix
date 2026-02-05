{
  pkgs,
  lib,
  config,
  ...
}: {
  # https://devenv.sh/languages/
  languages.java = {
    enable = true;
    jdk.package = pkgs.jdk17;
    gradle.enable = true;
  };

  # See full reference at https://devenv.sh/reference/options/
}
